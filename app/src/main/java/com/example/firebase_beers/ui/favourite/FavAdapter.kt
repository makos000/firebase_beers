package com.example.firebase_beers.ui.favourite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firebase_beers.R
import com.example.firebase_beers.databinding.RecyclerCardBinding
import com.example.firebase_beers.model.BeerModelItem
import com.example.firebase_beers.ui.MainViewModel
import com.example.firebase_beers.ui.main.MainAdapter


class FavAdapter(val favList: ArrayList<BeerModelItem>, val context: Context, val viewModel: MainViewModel): RecyclerView.Adapter<FavAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private lateinit var binding: RecyclerCardBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_card,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        var curItem = favList[position]

        binding = RecyclerCardBinding.bind(holder.itemView)

        Glide.with(context).load(curItem.imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.beer)).into(binding.imageView)

        binding.beerName.text = curItem.name
        binding.beerId.text = curItem.id.toString()


        holder.itemView.setOnClickListener(){

            val myList = arrayListOf<String>(curItem.imageUrl!!, curItem.name!!, curItem.description!!)

            val bundle = Bundle()
            bundle.putStringArrayList("beer",myList)

            it.findNavController().navigate(R.id.action_favouriteFragment_to_detailFragment, bundle)
        }


    }

    override fun getItemCount(): Int {

        return favList.size
    }
}