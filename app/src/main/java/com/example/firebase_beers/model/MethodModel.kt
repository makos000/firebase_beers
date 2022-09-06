package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class MethodModel(
    @SerializedName("fermentation")
    var fermentation: FermentationModel,
    @SerializedName("mash_temp")
    var mashTemp: List<MashTempModel>,
    @SerializedName("twist")
    var twist: String
)