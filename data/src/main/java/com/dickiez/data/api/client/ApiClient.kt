/***
 * Author         : Dicky Zulkarnain
 * Date           : 21/10/22
 * Original File  : ApiClient
 ***/

package com.dickiez.data.api.client

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


abstract class ApiClient : OkHttpClient() {

  abstract val headers : Map<String, Any>

  override fun newBuilder(): Builder {
    return Builder()
      .addInterceptor(baseHeaderInterceptor())
      .addInterceptor(httpLoggingInterceptor())
      .addNetworkInterceptor(cacheInterceptor())
      .writeTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .connectTimeout(30, TimeUnit.SECONDS)
  }

  private fun baseHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->
      val original = chain.request()
      val request = original.newBuilder().apply {
        headers.forEach { header(it.key, it.value.toString()) }
        method(original.method, original.body)
      }.build()
      chain.proceed(request)
    }
  }

  private fun cacheInterceptor(): Interceptor {
    return Interceptor { chain ->
      val response = chain.proceed(chain.request())
      val cacheControl = CacheControl.Builder()
        .maxAge(15, TimeUnit.SECONDS)
        .build()
      response.newBuilder()
        .header("Cache-Control", cacheControl.toString())
        .build()
    }
  }

  private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
  }

  fun build() = newBuilder().build()

}