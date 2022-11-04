/***
 * Author         : Dicky Zulkarnain
 * Date           : 22/10/22
 * Original File  : ApiCaller
 ***/

package com.dickiez.data.api

import com.dickiez.data.Completion
import com.dickiez.data.DataState
import com.dickiez.data.DataResult
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

open class ApiHandler {

  protected suspend fun <T> execute(result: (Completion<T>) -> Unit, apiCall: suspend () -> Response<T>?) {
    result(Completion(DataState.Loading))
    try {
      val res = apiCall.invoke()
      if (res != null) {
        if (res.isSuccessful) {
          val apiResponse = DataResult(statusCode = res.code(), body = res.body(), message = res.message())
          result(Completion(DataState.Success, apiResponse))
        } else {
          val errorResponse = DataResult<T>(statusCode = res.code(), errorMessage = res.errorBody().convert())
          result(Completion(DataState.Error, errorResponse))
        }
      } else {
        val errorResponse = DataResult<T>(statusCode = 500, errorMessage = res?.errorBody().convert())
        result(Completion(DataState.Error, errorResponse))
      }
    } catch (throwable: Throwable) {
      throwable.printStackTrace()
      result(Completion(DataState.Error, DataResult(throwable)))
    } finally {
      result(Completion(DataState.Finish))
    }
  }

  private fun ResponseBody?.convert() : String {
    var errorMessage = ""
    this?.let {
      try { errorMessage = it.string() }
      catch (e: IOException) { e.printStackTrace() }
    }
    return errorMessage
  }
}