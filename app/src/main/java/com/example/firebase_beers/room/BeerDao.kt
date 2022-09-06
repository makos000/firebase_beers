package com.example.firebase_beers.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firebase_beers.model.BeerModelItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertBeersToDB(beerEntity: BeerEntity)

    @Query("SELECT * FROM beers_table order by `index` DESC")
    fun readBeersFromDB(): Flow<List<BeerEntity>>

    @Query("DELETE FROM beers_table")
    fun nukeTable()

    @Query("DELETE FROM beers_table WHERE beerModel = :model")
    fun deleteSpecificModel(model: BeerModelItem)

}