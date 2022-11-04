package com.dickiez.data.database

import androidx.room.*
import com.dickiez.data.models.tmdb.Genre

@Dao
interface MovieDao {

  @Query("SELECT * FROM GENRE")
  suspend fun getGenres() : List<Genre>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertGenre(user: Genre)

}