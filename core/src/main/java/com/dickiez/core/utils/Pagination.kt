/***
 * Author         : Dicky Zulkarnain
 * Date           : 02/11/22
 * Original File  : Paginator
 ***/

package com.dickiez.core.utils


class Pagination(
  private var _page: Int = 0, // DEFAULT
  private var _hasMore: Boolean = true, // DEFAULT
) {

  private var _isLoadingMore = false

  val currentPage : Int get() = _page

  val isLoadingMore : Boolean get() = _isLoadingMore

  suspend fun loadNext(page: suspend (Int) -> Unit) {
    if (_hasMore) {
      _page++
      _isLoadingMore = _page > 1
      page.invoke(_page)
    }
  }

  fun update(page: Int? = null, hasMore: Boolean? = null) {
    this._page = page ?: this._page
    this._hasMore = hasMore ?: this._hasMore
    _isLoadingMore = false
  }

  fun reset() {
    _page = 0
    _hasMore = false
    _isLoadingMore = false
  }

}