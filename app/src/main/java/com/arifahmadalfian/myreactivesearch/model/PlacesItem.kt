package com.arifahmadalfian.myreactivesearch.model

import com.google.gson.annotations.SerializedName

data class PlacesItem(
    @field:SerializedName("places_name")
    val placeName: String
)
