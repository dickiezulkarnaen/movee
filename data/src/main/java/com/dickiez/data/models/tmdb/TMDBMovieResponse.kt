package com.dickiez.data.models.tmdb


import com.google.gson.annotations.SerializedName

data class TMDBMovieResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var results: List<MovieResult?>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)