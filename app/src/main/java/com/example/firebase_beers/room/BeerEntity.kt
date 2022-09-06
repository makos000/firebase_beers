package com.example.firebase_beers.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.firebase_beers.model.BeerModelItem

@Entity(tableName = "beers_table")
class BeerEntity(val beerModel: BeerModelItem) {
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0
}