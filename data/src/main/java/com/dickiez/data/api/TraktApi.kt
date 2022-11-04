/***
 * Author         : Dicky Zulkarnain
 * Date           : 23/10/22
 * Original File  : TraktApi
 ***/

package com.dickiez.data.api

import com.dickiez.data.models.trakt.TraktMovieResponse
import com.dickiez.data.models.trakt.TraktShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface TraktApi {

  @GET("shows/{path}")
  suspend fun getShows(@Path("path") path: String) : Response<TraktShowsResponse>

}