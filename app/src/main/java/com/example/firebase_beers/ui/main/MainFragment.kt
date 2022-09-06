package com.example.firebase_beers.ui.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_beers.R
import com.example.firebase_beers.databinding.FragmentMainBinding
import com.example.firebase_beers.ui.MainViewModel
import com.example.firebase_beers.util.CheckInternet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val viewModel by viewModels<MainViewModel>()
        adapter = MainAdapter(viewModel.favList,requireActivity(),viewModel)

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.favButton.setOnClickListener(){
            it.findNavController().navigate(R.id.action_mainFragment_to_favouriteFragment)
        }

        val recycler = binding.recyclerV
        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager

        val progressBar = binding.progressBar

        viewModel.readFav.observe(requireActivity()){
            for (beer in it){
                viewModel.favList.add(beer.beerModel)
                //viewModel.delSpecific(beer.beerModel)
            }
        }

        val internet = CheckInternet()

        var internetBool = internet.internetIsConnected()

        if (internetBool){
            viewModel.getData(viewModel.cur_page,10)

            viewModel._data.observe(requireActivity()){
                adapter.notifyDataSetChanged()
            }
        }

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy>0){
                    val visibeItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount

                    if (viewModel.isLoading == false){
                        if (visibeItemCount+ pastVisibleItem >= total){

                            if (internetBool){
                                handleRecycler(viewModel, progressBar, recycler)
                            }
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        adapter = MainAdapter(viewModel.favList,requireActivity(),viewModel)
        recycler.adapter = adapter

        return binding.root
    }

    private fun handleRecycler(
        viewModel: MainViewModel,
        progressBar: ProgressBar,
        recycler: RecyclerView
    ) {
        viewModel.isLoading = true
        progressBar.visibility = View.VISIBLE

        viewModel.cur_page++
        //viewModel.nukeData()
        viewModel.getData(viewModel.cur_page, 10)

        Handler().postDelayed({
            if (::adapter.isInitialized) {
                adapter.notifyDataSetChanged()
            } else {

                adapter = MainAdapter(viewModel.favList, requireActivity(), viewModel)
                recycler.adapter = adapter
            }
            progressBar.visibility = View.GONE
            viewModel.isLoading = false
        }, 500)
    }
}