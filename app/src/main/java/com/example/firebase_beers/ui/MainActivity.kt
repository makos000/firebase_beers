package com.example.firebase_beers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.firebase_beers.R
import com.example.firebase_beers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel by viewModels<MainViewModel>()

        //viewModel.nukeData()
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_fragmentID) as NavHostFragment

        navController = navHostFragment.navController

        //setupActionBarWithNavController(navController)

        setContentView(binding.root)

        //navController.navigate(R.id.action_mainFragment_to_loginFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}