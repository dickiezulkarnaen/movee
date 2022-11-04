/***
 * Author         : Dicky Zulkarnain
 * Date           : 22/10/22
 * Original File  : TheMovieDatabaseApiClient
 ***/

package com.dickiez.data.api.client


class TheMovieDatabaseApiClient : ApiClient() {

  override val headers: Map<String, Any>
    get() = Headers.BASE_HEADERS

}