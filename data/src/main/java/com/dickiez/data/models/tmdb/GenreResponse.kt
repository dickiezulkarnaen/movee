package com.dickiez.data.models.tmdb


import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    var genres: List<Genre>? = listOf()
)