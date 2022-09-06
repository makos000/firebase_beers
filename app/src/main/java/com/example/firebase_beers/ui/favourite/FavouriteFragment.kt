package com.example.firebase_beers.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_beers.R
import com.example.firebase_beers.databinding.FragmentFavouriteBinding
import com.example.firebase_beers.databinding.FragmentMainBinding
import com.example.firebase_beers.model.BeerModelItem
import com.example.firebase_beers.ui.MainViewModel
import com.example.firebase_beers.ui.main.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment: Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

    //private lateinit var adapter: FavAdapter

    //private lateinit var adapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        val viewModel by viewModels<MainViewModel>()

        //adapter = FavAdapter(viewModel.favList,requireActivity(),viewModel)

        val recycler = binding.recyclerVfav
        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager

        val adapter = FavAdapter(viewModel.favList,requireActivity(),viewModel)
        viewModel.readFav.observe(requireActivity()){
            for (beer in it){
                viewModel.favList.add(beer.beerModel)
            }

            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }


        binding.button.setOnClickListener(){
            viewModel.nukeData()
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }




        return binding.root
    }
}