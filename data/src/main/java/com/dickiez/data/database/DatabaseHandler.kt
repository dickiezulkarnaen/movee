/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : DatabaseHandler
 ***/

package com.dickiez.data.database

import com.dickiez.data.Completion
import com.dickiez.data.DataState
import com.dickiez.data.DataResult
import java.lang.Exception


open class DatabaseHandler {

  protected suspend fun <T> execute(result: (Completion<T>) -> Unit, access: suspend () -> T?) {
    result(Completion(DataState.Loading))
    try {
      val res = access.invoke()
      if (res != null) {
        val apiResponse = DataResult<T>(isSuccessful = true, data = res, message = "")
        result(Completion(DataState.Success, result = apiResponse))
      } else {
        val errorResponse = DataResult<T>(isSuccessful = false, null)
        result(Completion(DataState.Error, errorResponse))
      }
    } catch (ex : Exception) {
      val errorResponse = DataResult<T>(isSuccessful = false, null)
      result(Completion(DataState.Error, errorResponse))
    }
  }

}