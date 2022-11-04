package com.dickiez.data.models.tmdb


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int? = null,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null
)