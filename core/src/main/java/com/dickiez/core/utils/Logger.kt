/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : Logger
 ***/

package com.dickiez.core.utils

import android.util.Log

object Logger {

  fun init(isDebug: Boolean) {
    isDebugMode = isDebug
  }

  private var isDebugMode: Boolean = true

  fun d(tag: String, message: String) {
    if (isDebugMode) Log.d(tag, "$SIGN $message")
  }

  fun w(tag: String, message: String) {
    if (isDebugMode) Log.w(tag, "$SIGN $message")
  }

  fun i(tag: String, message: String) {
    if (isDebugMode) Log.i(tag, "$SIGN $message")
  }

  fun wtf(tag: String, message: String) {
    if (isDebugMode) Log.wtf(tag, "$SIGN $message")
  }

  fun e(tag: String, message: String?, throwable: Throwable? = null) {
    if (isDebugMode) Log.e(tag, "$SIGN $message", throwable)
  }

  private const val SIGN = "===>"

}