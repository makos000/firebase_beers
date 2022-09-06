package com.example.firebase_beers.ui.main

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


class MainAdapter(val favList: ArrayList<BeerModelItem>,val context: Context, val viewModel: MainViewModel): RecyclerView.Adapter<MainAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private lateinit var binding: RecyclerCardBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_card,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        var curItem = viewModel.recycler_list[position]


        binding = RecyclerCardBinding.bind(holder.itemView)

        Glide.with(context).load(curItem.imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.beer)).into(binding.imageView)

        binding.beerName.text = curItem.name
        binding.beerId.text = curItem.id.toString()

        //viewModel.delSpecific(curItem)

        for (item in favList){
            if(curItem.name == item.name){
                curItem.fav = true
            }
        }

        if (curItem.fav == false){
            binding.favButton.setBackgroundResource(R.drawable.star_fill0)
        }
        else{
            binding.favButton.setBackgroundResource(R.drawable.star_fill1)
        }

        binding.favButton.setOnClickListener(){
            if (curItem.fav == false){
                curItem.fav = true
                binding.favButton.setBackgroundResource(R.drawable.star_fill1)
                notifyDataSetChanged()
                viewModel.insertIntoDatabase(curItem)
            }
            else{
                curItem.fav = false
                binding.favButton.setBackgroundResource(R.drawable.star_fill0)
                notifyDataSetChanged()
                viewModel.delSpecific(curItem)
            }
            Log.i("Button","Fav button clicked")
        }


        holder.itemView.setOnClickListener(){

            val myList = arrayListOf<String>(curItem.imageUrl!!, curItem.name!!, curItem.description!!)

            val bundle = Bundle()
            bundle.putStringArrayList("beer",myList)

            it.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }


    }

    override fun getItemCount(): Int {

        return viewModel.recycler_list.size
    }
}