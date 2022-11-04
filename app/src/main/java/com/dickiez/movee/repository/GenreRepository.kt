/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : GenreRepository
 ***/

package com.dickiez.movee.repository

import com.dickiez.data.Completion
import com.dickiez.data.DataResult
import com.dickiez.data.DataState
import com.dickiez.data.models.tmdb.Genre
import com.dickiez.data.source.GenreLocalDataSource
import com.dickiez.data.source.GenreRemoteDataSource
import kotlinx.coroutines.*
import javax.inject.Inject


@OptIn(DelicateCoroutinesApi::class)
class GenreRepository @Inject constructor(
  private val remoteDataSource: GenreRemoteDataSource,
  private val localDataSource: GenreLocalDataSource,
) {

  suspend fun getGenres(result: (Completion<List<Genre>>) -> Unit) {
    // GET DATA FROM LOCAL FIRST
    localDataSource.getGenres {
      // CHECK NULL OR EMPTY ON LOCAL
      if (it.state == DataState.Error || (it.state == DataState.Success && it.result?.data?.isEmpty() != false)) {
        // IF TRUE, GET DATA FROM REMOTE
        GlobalScope.launch {
          remoteDataSource.getGenres { completion ->
            val remoteResult = DataResult(completion.result?.isSuccessful ?: false, completion.result?.data?.genres)
            val remoteCompletion = Completion(completion.state, remoteResult)
            remoteCompletion.result?.data?.let { list ->
              saveGenresToLocal(list)
            }
            result(remoteCompletion)
          }
        }
      }
      else result(it)
    }
  }

  private fun saveGenresToLocal(genres: List<Genre>) = GlobalScope.launch {
    localDataSource.saveGenres(genres)
  }

}