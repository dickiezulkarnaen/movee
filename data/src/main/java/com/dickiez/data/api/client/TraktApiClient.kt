/***
 * Author         : Dicky Zulkarnain
 * Date           : 23/10/22
 * Original File  : TraktApiClient
 ***/

package com.dickiez.data.api.client

import com.dickiez.core.constants.ApiConstants
import com.dickiez.data.BuildConfig


class TraktApiClient : ApiClient() {
  override val headers: Map<String, Any>
    get() = HashMap<String, Any>().apply {
      this.putAll(Headers.BASE_HEADERS)
      this["trakt-api-key"] = BuildConfig.TRAKT_CLIENT_ID
      this["trakt-api-version"] = ApiConstants.Trakt.TRAKT_API_VERSION
    }

}