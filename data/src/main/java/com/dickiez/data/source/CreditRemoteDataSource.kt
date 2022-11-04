/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : CreditRemoteDataSource
 ***/

package com.dickiez.data.source

import com.dickiez.data.BuildConfig
import com.dickiez.data.Completion
import com.dickiez.data.api.ApiHandler
import com.dickiez.data.api.TheMovieDatabaseApi
import com.dickiez.data.models.tmdb.CreditResponse
import javax.inject.Inject


class CreditRemoteDataSource @Inject constructor(private val tmdbApi: TheMovieDatabaseApi) : ApiHandler() {

  suspend fun getCredit(movieId: Int, result: (Completion<CreditResponse>) -> Unit) {
    execute(result) {
      tmdbApi.getCredit(movieId.toString(), BuildConfig.TMDB_API_KEY)
    }
  }
}