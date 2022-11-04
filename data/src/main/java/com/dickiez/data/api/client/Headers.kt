package com.dickiez.data.api.client

object Headers {
  val BASE_HEADERS : HashMap<String, Any> = HashMap<String, Any>().apply {
    this["User-Agent"] = System.getProperty("http.agent") ?: "Base Agent"
    this["Accept"] = "application/json"
  }
}