package com.example.firebase_beers.repo

import com.example.firebase_beers.model.BeerModelItem
import com.example.firebase_beers.room.BeerEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Query

interface RepoInterface {
    suspend fun getRecipes(@Query("page") page: Int, @Query("per_page") per_page: Int): Response<ArrayList<BeerModelItem>>

    fun insertBeersToDB(beerEntity: BeerEntity)

    fun readBeersFromDB(): Flow<List<BeerEntity>>

    fun nukeTable()

    fun deleteSpecificModel(model: BeerModelItem)
}