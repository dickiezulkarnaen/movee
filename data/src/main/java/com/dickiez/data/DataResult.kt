/***
 * Author         : Dicky Zulkarnain
 * Date           : 22/10/22
 * Original File  : ApiResponse
 ***/

package com.dickiez.data


class DataResult<T> {
  val data: T?
  val error: String?
  val isSuccessful: Boolean

  constructor(error: Throwable) {
    this.data = null
    this.error = error.message
    this.isSuccessful = false
  }

  constructor(statusCode: Int, errorMessage: String? = null) {
    this.isSuccessful = statusCode in 200..300
    this.data = null
    this.error = errorMessage
  }

  constructor(statusCode: Int, body: T?, message: String? = null) {
    this.isSuccessful = statusCode in 200..300
    if (statusCode in 200..300) {
      this.data = body
      this.error = null
    } else {
      this.data = null
      this.error = message
    }
  }

  constructor(isSuccessful: Boolean, data: T?, message: String? = null) {
    this.isSuccessful = isSuccessful
    this.data = data
    this.error = message
  }

  companion object {
    const val DEFAULT_ERROR_MESSAGE = "Ups, something wrong!"
  }
}