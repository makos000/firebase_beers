package com.example.firebase_beers.repo

import android.os.Build.ID
import com.example.firebase_beers.api.ApiInterface
import com.example.firebase_beers.model.BeerModelItem
import com.example.firebase_beers.room.BeerDao
import com.example.firebase_beers.room.BeerEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepoImpl @Inject constructor(val apiInterface: ApiInterface, val beerDao: BeerDao): RepoInterface {
    override suspend fun getRecipes(page: Int, per_page: Int): Response<ArrayList<BeerModelItem>> {
        return apiInterface.getRecipes(page, per_page)
    }

    override fun insertBeersToDB(beerEntity: BeerEntity) {
        return beerDao.insertBeersToDB(beerEntity)
    }

    override fun readBeersFromDB(): Flow<List<BeerEntity>> {
        return beerDao.readBeersFromDB()
    }

    override fun nukeTable() {
        return beerDao.nukeTable()
    }

    override fun deleteSpecificModel(model: BeerModelItem) {
        return beerDao.deleteSpecificModel(model)
    }
}