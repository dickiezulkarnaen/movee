/***
 * Author         : Dicky Zulkarnain
 * Date           : 31/10/22
 * Original File  : MovieCategory
 ***/

package com.dickiez.core.constants


sealed class MovieCategory(val key: String, val value: String) {
  object Popular: MovieCategory("popular", "Popular")
  object TopRated: MovieCategory("top_rated", "Top Rated")
  object NowPlaying: MovieCategory("now_playing", "Now Playing")
  object Upcoming: MovieCategory("upcoming", "Upcoming")

  companion object {
    fun fromString(value : String) : MovieCategory? {
      return when(value) {
        Popular.key -> Popular
        TopRated.key -> TopRated
        NowPlaying.key -> NowPlaying
        Upcoming.key -> Upcoming
        else -> null
      }
    }
  }
}