/***
 * Author         : Dicky Zulkarnain
 * Date           : 21/10/22
 * Original File  : TheMovieDatabaseApi
 ***/

package com.dickiez.data.api

import com.dickiez.data.models.tmdb.CreditResponse
import com.dickiez.data.models.tmdb.GenreResponse
import com.dickiez.data.models.tmdb.MovieDetailResponse
import com.dickiez.data.models.tmdb.TMDBMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDatabaseApi {

  @GET("movie/{category}")
  suspend fun getMovies(
    @Path("category") category: String,
    @Query("page") page: Int,
    @Query("api_key") apiKey: String
  ): Response<TMDBMovieResponse>

  @GET("movie/{movie_id}")
  suspend fun getMovie(
    @Path("movie_id") movieId: String,
    @Query("api_key") apiKey: String
  ): Response<MovieDetailResponse>

  @GET("genre/movie/list")
  suspend fun getGenres(
    @Query("api_key") apiKey: String
  ): Response<GenreResponse>

  @GET("movie/{movie_id}}/credits")
  suspend fun getCredit(
    @Path("movie_id") movieId: String,
    @Query("api_key") apiKey: String
  ): Response<CreditResponse>

}