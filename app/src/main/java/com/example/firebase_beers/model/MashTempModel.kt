package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class MashTempModel(
    @SerializedName("duration")
    var duration: Int
)