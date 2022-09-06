package com.example.firebase_beers.model


import com.google.gson.annotations.SerializedName

data class BeerModelItem(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("abv")
    val abv: Double? = 0.0,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double? = 0.0,
    @SerializedName("boil_volume")
    val boilVolume: BoilVolumeModel? = null,
    @SerializedName("fav")
    var fav: Boolean? = false,
    /*@SerializedName("abv")
    var abv: Double,
    @SerializedName("attenuation_level")
    var attenuationLevel: Double,
    @SerializedName("boil_volume")
    var boilVolume: BoilVolumeModel,
    @SerializedName("brewers_tips")
    var brewersTips: String,
    @SerializedName("contributed_by")
    var contributedBy: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("ebc")
    var ebc: Double,
    @SerializedName("first_brewed")
    var firstBrewed: String,
    @SerializedName("food_pairing")
    var foodPairing: List<String>,
    @SerializedName("ibu")
    var ibu: Double,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image_url")
    var imageUrl: String,
    @SerializedName("ingredients")
    var ingredients: IngredientsModel,
    @SerializedName("method")
    var method: MethodModel,
    @SerializedName("name")
    var name: String,
    @SerializedName("ph")
    var ph: Double,
    @SerializedName("srm")
    var srm: Double,
    @SerializedName("tagline")
    var tagline: String,
    @SerializedName("target_fg")
    var targetFg: Int,
    @SerializedName("target_og")
    var targetOg: Double,
    @SerializedName("volume")
    var volume: VolumeModel*/
)