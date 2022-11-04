package com.dickiez.data.models.trakt


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("ids")
    var ids: Ids? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("year")
    var year: Int? = null
)