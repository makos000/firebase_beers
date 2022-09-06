package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class MaltModel(
    @SerializedName("amount")
    var amount: AmountModel,
    @SerializedName("name")
    var name: String
)