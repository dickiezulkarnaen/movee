/***
 * Author         : Dicky Zulkarnain
 * Date           : 29/10/22
 * Original File  : MovieDetailViewModel
 ***/

package com.dickiez.movee.modules.detail

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.dickiez.core.utils.mapNotNullOrEmpty
import com.dickiez.data.DataState
import com.dickiez.data.models.tmdb.Cast
import com.dickiez.data.models.tmdb.MovieDetailResponse
import com.dickiez.movee.base.BaseViewModel
import com.dickiez.movee.repository.CreditRepository
import com.dickiez.movee.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
  private val movieRepository: MovieRepository,
  private val creditRepository: CreditRepository,
) : BaseViewModel() {

  private val _movieDetail = MutableStateFlow(MovieDetailResponse())
  private val _casts = mutableStateListOf<Cast>()
  private val _director = MutableStateFlow("")

  val movieDetail = _movieDetail.asStateFlow()
  val casts : List<Cast> = _casts
  val director = _director.asStateFlow()

  fun getMovie(id: Int) = viewModelScope.launch {
    movieRepository.getMovie(id) {
      if (it.state == DataState.Success) {
        it.result?.data?.let { data -> _movieDetail.value = data }
      }
    }
  }

  fun getCredit(movieId: Int) = viewModelScope.launch {
    creditRepository.getCredit(movieId) {
      if (it.state == DataState.Success) {
        _casts.clear()
        _casts.addAll(it.result?.data?.cast.mapNotNullOrEmpty().subList(0, 5))
        _director.value = it.result?.data?.crew?.find { crew -> crew?.job == "Director" }?.name ?: "Unknown"
      }
    }
  }

}