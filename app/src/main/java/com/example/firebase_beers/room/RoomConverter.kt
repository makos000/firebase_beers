package com.example.firebase_beers.room

import androidx.room.TypeConverter
import com.example.firebase_beers.model.BeerModelItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {
    var gson = Gson()

    @TypeConverter
    fun beersToString(beerModel: BeerModelItem): String = gson.toJson(beerModel)

    @TypeConverter
    fun stringToBeers(data: String): BeerModelItem {
        val listType = object : TypeToken<BeerModelItem>() {}.type
        return gson.fromJson(data, listType)
    }
}