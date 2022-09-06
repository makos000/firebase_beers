package com.example.firebase_beers.api

import com.example.firebase_beers.model.BeerModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(ApiResources.END_POINT)
    suspend fun getRecipes(@Query("page") page: Int, @Query("per_page") per_page: Int): Response<ArrayList<BeerModelItem>>

}