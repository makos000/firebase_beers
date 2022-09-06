package com.example.firebase_beers.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.firebase_beers.model.BeerModelItem
import com.example.firebase_beers.repo.RepoInterface
import com.example.firebase_beers.room.BeerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repoInterface: RepoInterface): ViewModel() {

    val _data: MutableLiveData<ArrayList<BeerModelItem>> = MutableLiveData(arrayListOf(BeerModelItem()))
    var recycler_list : ArrayList<BeerModelItem> = arrayListOf()
    var favList : ArrayList<BeerModelItem> = arrayListOf()
    var cur_page = 1
    var isLoading = false


    fun getData(page: Int, per_page: Int){

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repoInterface.getRecipes(page,per_page)
                if(response.isSuccessful){
                    if (response.body()!!.isNotEmpty()){
                        response.body().let{ beers ->

                            if (beers != null) {
                                for (beer in beers){
                                    recycler_list.add(beer)
                                    //insertIntoDatabase(beer!!)
                                }
                            }
                            _data.postValue(recycler_list)

                        }
                    }

                }
                else{
                    Log.i("Response", "Api Response not successful")
                }
            }catch (e: Error){
                Error(e)
            }

        }
    }

    fun insertIntoDatabase(model: BeerModelItem){
        val beerEntity = BeerEntity(model)
        CoroutineScope(Dispatchers.IO).launch {
            repoInterface.insertBeersToDB(beerEntity)
        }
    }

    val readFav: LiveData<List<BeerEntity>> = repoInterface.readBeersFromDB().asLiveData()

    fun nukeData(){
        CoroutineScope(Dispatchers.IO).launch {
            repoInterface.nukeTable()
        }
    }

    fun delSpecific(model: BeerModelItem){
        CoroutineScope(Dispatchers.IO).launch {
            repoInterface.deleteSpecificModel(model)
        }
    }
}