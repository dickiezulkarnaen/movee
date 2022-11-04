package com.dickiez.data.models.trakt


import com.google.gson.annotations.SerializedName

data class Ids(
    @SerializedName("imdb")
    var imdb: String? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("tmdb")
    var tmdb: Int? = null,
    @SerializedName("trakt")
    var trakt: Int? = null,
    @SerializedName("tvdb")
    var tvdb: Int? = null,
    @SerializedName("tvrage")
    var tvrage: Int? = null
)