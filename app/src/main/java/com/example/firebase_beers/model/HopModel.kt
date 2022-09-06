package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class HopModel(
    @SerializedName("add")
    var add: String,
    @SerializedName("amount")
    var amount: AmountModel,
    @SerializedName("attribute")
    var attribute: String,
    @SerializedName("name")
    var name: String
)