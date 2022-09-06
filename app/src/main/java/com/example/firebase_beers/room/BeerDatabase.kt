package com.example.firebase_beers.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BeerEntity::class],
    version = 1,
    exportSchema = false
)


@TypeConverters(RoomConverter::class)
abstract class BeerDatabase: RoomDatabase() {
    abstract fun beerDao(): BeerDao
}