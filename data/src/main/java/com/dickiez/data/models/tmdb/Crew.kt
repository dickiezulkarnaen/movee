package com.dickiez.data.models.tmdb


import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("credit_id")
    var creditId: String? = null,
    @SerializedName("department")
    var department: String? = null,
    @SerializedName("gender")
    var gender: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("job")
    var job: String? = null,
    @SerializedName("known_for_department")
    var knownForDepartment: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("original_name")
    var originalName: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @SerializedName("profile_path")
    var profilePath: String? = null
)