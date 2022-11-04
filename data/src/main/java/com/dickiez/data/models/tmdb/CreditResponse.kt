package com.dickiez.data.models.tmdb


import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @SerializedName("cast")
    var cast: List<Cast?>? = null,
    @SerializedName("crew")
    var crew: List<Crew?>? = null,
    @SerializedName("id")
    var id: Int? = null
)