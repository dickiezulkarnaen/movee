/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : GenreRemoteDataSource
 ***/

package com.dickiez.data.source

import com.dickiez.data.BuildConfig
import com.dickiez.data.api.ApiHandler
import com.dickiez.data.Completion
import com.dickiez.data.api.TheMovieDatabaseApi
import com.dickiez.data.models.tmdb.GenreResponse
import javax.inject.Inject


class GenreRemoteDataSource @Inject constructor(private val tmdbApi: TheMovieDatabaseApi) : ApiHandler() {

  suspend fun getGenres(result: (Completion<GenreResponse>) -> Unit) {
    execute(result) {
      tmdbApi.getGenres(BuildConfig.TMDB_API_KEY)
    }
  }

}