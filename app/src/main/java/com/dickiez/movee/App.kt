/***
 * Author         : Dicky Zulkarnain
 * Date           : 21/10/22
 * Original File  : App
 ***/

package com.dickiez.movee

import android.app.Application
import com.dickiez.core.utils.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

  override fun onCreate() {
    super.onCreate()
    Logger.init(BuildConfig.DEBUG)
  }
}