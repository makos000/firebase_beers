package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class IngredientsModel(
    @SerializedName("hops")
    var hops: List<HopModel>,
    @SerializedName("malt")
    var malt: List<MaltModel>,
    @SerializedName("yeast")
    var yeast: String
)