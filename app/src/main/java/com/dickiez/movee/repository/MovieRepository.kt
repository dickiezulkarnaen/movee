/***
 * Author         : Dicky Zulkarnain
 * Date           : 22/10/22
 * Original File  : MainRepository
 ***/

package com.dickiez.movee.repository

import com.dickiez.data.Completion
import com.dickiez.data.models.tmdb.MovieDetailResponse
import com.dickiez.data.models.tmdb.TMDBMovieResponse
import com.dickiez.data.source.MovieRemoteDataSource
import javax.inject.Inject


class MovieRepository @Inject constructor(private val remoteDataSource: MovieRemoteDataSource) {

  suspend fun getMovies(category: String, page: Int, result: (Completion<TMDBMovieResponse>) -> Unit) {
    remoteDataSource.getMovies(category, page, result)
  }

  suspend fun getMovie(movieId: Int, result: (Completion<MovieDetailResponse>) -> Unit) {
    remoteDataSource.getMovie(movieId, result)
  }

}