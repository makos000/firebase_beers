package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class FermentationModel(
    @SerializedName("temp")
    var temp: TempModel
)