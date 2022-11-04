package com.dickiez.data.models.trakt


import com.google.gson.annotations.SerializedName

data class TraktShowsResponseItem(
    @SerializedName("show")
    var show: Show? = Show(),
    @SerializedName("watchers")
    var watchers: Int? = 0
)