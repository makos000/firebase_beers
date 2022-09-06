package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class BoilVolumeModel(
    @SerializedName("unit")
    var unit: String,
    @SerializedName("value")
    var value: Int
)