/***
 * Author         : Dicky Zulkarnain
 * Date           : 22/10/22
 * Original File  : MainViewModel
 ***/

package com.dickiez.movee.modules.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.dickiez.core.constants.MovieCategory
import com.dickiez.data.DataState
import com.dickiez.data.models.tmdb.MovieResult
import com.dickiez.movee.base.BaseViewModel
import com.dickiez.movee.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository) : BaseViewModel() {

  private val _popular = mutableStateListOf<MovieResult>()
  private val _topRated = mutableStateListOf<MovieResult>()
  private val _nowPlaying = mutableStateListOf<MovieResult>()
  private val _upcoming = mutableStateListOf<MovieResult>()

  val popular : List<MovieResult> = _popular
  val topRated : List<MovieResult> = _topRated
  val nowPlaying : List<MovieResult> = _nowPlaying
  val upcoming : List<MovieResult> = _upcoming

  init {
    getAllData()
  }

  // GET DATA IN PARALLEL AND WAIT EACH OTHER FOR FINISH
  private fun getAllData() = viewModelScope.launch {
    setLoading(true)
    val job1 = launch { getPopularMovies() }
    val job2 = launch { getTopRatedMovies() }
    val job3 = launch { getNowPlayingMovies() }
    val job4 = launch { getUpcomingMovies() }
    job1.join()
    job2.join()
    job3.join()
    job4.join()
    setLoading(false)
  }

  private suspend fun getPopularMovies() {
    repository.getMovies(MovieCategory.Popular.key, 1) {
      when (it.state) {
        DataState.Success -> {
          val data = populateData(it.result?.data?.results)
          _popular.addAll(data)
        }
        DataState.Error -> showErrorMessage(it.result?.error)
        else -> {}
      }
    }
  }

  private suspend fun getTopRatedMovies() {
    repository.getMovies(MovieCategory.TopRated.key, 1) {
      when (it.state) {
        DataState.Success -> {
          val data = populateData(it.result?.data?.results)
          _topRated.addAll(data)
        }
        DataState.Error -> showErrorMessage(it.result?.error)
        else -> {}
      }
    }
  }

  private suspend fun getNowPlayingMovies() {
    repository.getMovies(MovieCategory.NowPlaying.key, 1) {
      if (it.state == DataState.Success) {
          val data = populateData(it.result?.data?.results)
          _nowPlaying.addAll(data)
      } else if (it.state == DataState.Error) {
        showErrorMessage(it.result?.error)
      }
    }
  }

  private suspend fun getUpcomingMovies() {
    repository.getMovies(MovieCategory.Upcoming.key, 1) {
      when (it.state) {
        DataState.Success -> {
          val data = populateData(it.result?.data?.results)
          _upcoming.addAll(data)
        }
        DataState.Error -> showErrorMessage(it.result?.error)
        else -> {}
      }
    }
  }

  private fun populateData(data: List<MovieResult?>?) : List<MovieResult> {
    return data?.mapNotNull { it } ?: listOf()
  }

}