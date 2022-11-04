/***
 * Author         : Dicky Zulkarnain
 * Date           : 21/10/22
 * Original File  : ApiService
 ***/

package com.dickiez.data.api

import com.dickiez.data.api.client.ApiClient
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

  inline fun <reified T> getRetrofit(baseUrl: String, apiClient: ApiClient) : T {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(apiClient.build())
      .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
      .build()
      .create(T::class.java)
  }

}