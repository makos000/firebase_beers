package com.example.firebase_beers.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firebase_beers.R
import com.example.firebase_beers.databinding.FragmentDetailBinding
import com.example.firebase_beers.databinding.FragmentMainBinding
import com.example.firebase_beers.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class DetailFragment: Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    //private lateinit var adapter: MainAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<MainViewModel>()

        binding = FragmentDetailBinding.bind(view)

        val myData = requireArguments().get("beer") as ArrayList<String>

        Glide.with(requireActivity()).load(myData[0])
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(binding.imageView)

        binding.textView.text = myData[1]
        binding.desc.text = myData[2]
    }
}