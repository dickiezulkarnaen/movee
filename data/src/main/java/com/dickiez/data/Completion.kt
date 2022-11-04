/***
 * Author         : Dicky Zulkarnain
 * Date           : 22/10/22
 * Original File  : ApiResult
 ***/

package com.dickiez.data


data class Completion<T>(
  val state: DataState,
  val result: DataResult<T>? = null
)