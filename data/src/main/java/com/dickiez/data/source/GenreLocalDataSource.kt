/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : GenreLocalDataSource
 ***/

package com.dickiez.data.source

import com.dickiez.data.Completion
import com.dickiez.data.database.DatabaseHandler
import com.dickiez.data.database.MovieDatabase
import com.dickiez.data.models.tmdb.Genre
import javax.inject.Inject


class GenreLocalDataSource @Inject constructor(private val movieDatabase: MovieDatabase) : DatabaseHandler() {

  suspend fun getGenres(result: (Completion<List<Genre>>) -> Unit) {
    execute(result) {
      movieDatabase.dao().getGenres()
    }
  }

  suspend fun saveGenres(genres: List<Genre>) {
    genres.forEach { movieDatabase.dao().insertGenre(it) }
  }
}