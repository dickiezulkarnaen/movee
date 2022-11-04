/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : CreditRepository
 ***/

package com.dickiez.movee.repository

import com.dickiez.data.Completion
import com.dickiez.data.models.tmdb.CreditResponse
import com.dickiez.data.source.CreditRemoteDataSource
import javax.inject.Inject


class CreditRepository @Inject constructor(private val creditRemoteDataSource: CreditRemoteDataSource) {

  suspend fun getCredit(movieId: Int, result: (Completion<CreditResponse>) -> Unit) {
    creditRemoteDataSource.getCredit(movieId, result)
  }

}