/***
 * Author         : Dicky Zulkarnain
 * Date           : 22/10/22
 * Original File  : MovieRemoteDataSource
 ***/

package com.dickiez.data.source

import com.dickiez.data.BuildConfig
import com.dickiez.data.api.ApiHandler
import com.dickiez.data.Completion
import com.dickiez.data.api.TheMovieDatabaseApi
import com.dickiez.data.api.TraktApi
import com.dickiez.data.models.tmdb.MovieDetailResponse
import com.dickiez.data.models.tmdb.TMDBMovieResponse
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
  private val tmdbApi: TheMovieDatabaseApi,
  private val traktApi: TraktApi
) : ApiHandler() {

  suspend fun getMovies(category: String, page: Int, result: (Completion<TMDBMovieResponse>) -> Unit) {
    execute(result) { tmdbApi.getMovies(category, page, BuildConfig.TMDB_API_KEY) }
  }
  suspend fun getMovie(movieId: Int, result: (Completion<MovieDetailResponse>) -> Unit) {
    execute(result) { tmdbApi.getMovie(movieId.toString(), BuildConfig.TMDB_API_KEY) }
  }

}