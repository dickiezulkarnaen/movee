package com.dickiez.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dickiez.data.models.tmdb.Genre

@Database(entities = [Genre::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

  abstract fun dao() : MovieDao

  companion object {
    const val DB_NAME = "movie.db"
  }

}