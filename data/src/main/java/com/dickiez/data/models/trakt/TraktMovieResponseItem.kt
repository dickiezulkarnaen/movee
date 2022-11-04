package com.dickiez.data.models.trakt


import com.google.gson.annotations.SerializedName

data class TraktMovieResponseItem(
    @SerializedName("movie")
    var movie: Movie? = null,
    @SerializedName("watchers")
    var watchers: Int? = null
)