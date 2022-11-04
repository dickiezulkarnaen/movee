/***
 * Author         : Dicky Zulkarnain
 * Date           : 31/10/22
 * Original File  : ListMovieViewModel
 ***/

package com.dickiez.movee.modules.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.dickiez.core.constants.MovieCategory
import com.dickiez.core.utils.Pagination
import com.dickiez.core.utils.mapNotNullOrEmpty
import com.dickiez.data.DataState
import com.dickiez.data.models.tmdb.Genre
import com.dickiez.data.models.tmdb.MovieResult
import com.dickiez.movee.base.BaseViewModel
import com.dickiez.movee.repository.GenreRepository
import com.dickiez.movee.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
  private val movieRepository: MovieRepository,
  private val genreRepository: GenreRepository
) : BaseViewModel() {

  val pagination = Pagination()

  private var _currentCategory : MovieCategory = MovieCategory.Popular

  private val _movies = mutableStateListOf<MovieResult>()
  private val _genres = mutableStateListOf<Genre>()

  val movies : List<MovieResult> = _movies
  val currentCategory get() = _currentCategory

  fun setCategory(category: MovieCategory) {
    _currentCategory = category
  }

  fun fetchInitialData() = viewModelScope.launch {
    setLoading(true)
    getGenres()
    getMovies()
    //simulatePagination()
    setLoading(false)
  }

  private fun simulatePagination() = viewModelScope.launch {
    getMovies()
    delay(2000)
    getMovies()
    delay(2000)
    getMovies()
  }

  private fun getGenres() = viewModelScope.launch {
    genreRepository.getGenres {
      if (it.state == DataState.Success) {
        _genres.clear()
        _genres.addAll(it.result?.data.mapNotNullOrEmpty())
      }
    }
  }

  fun getMovies() = viewModelScope.launch {
    pagination.loadNext { page ->
      setLoading(true)
      movieRepository.getMovies(currentCategory.key, page) {
        when (it.state) {
          DataState.Success -> viewModelScope.launch {
            delay(3000)
            setLoading(false)
            val data = it.result?.data?.results.mapNotNullOrEmpty()
            _movies.addAll(data)
            pagination.update(it.result?.data?.page, _movies.size < (it.result?.data?.totalPages ?: _movies.size))
          }
          DataState.Error -> showErrorMessage(it.result?.error)
          else -> {} //setLoading(it.state == DataState.Loading)
        }
      }
    }
  }

  fun mapGenreIdsToNames(ids: List<Int?>?) : String? {
    return ids?.map { getGenreNameById(it) }?.joinToString(", ")
  }

  private fun getGenreNameById(id: Int?): String? {
    return _genres.find { it.id == id }?.name
  }

}