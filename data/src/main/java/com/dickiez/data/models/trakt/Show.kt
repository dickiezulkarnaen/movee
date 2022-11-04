package com.dickiez.data.models.trakt


import com.google.gson.annotations.SerializedName

data class Show(
  @SerializedName("ids")
    var ids: Ids? = Ids(),
  @SerializedName("title")
    var title: String? = "",
  @SerializedName("year")
    var year: Int? = 0
)