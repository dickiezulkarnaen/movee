/***
 * Author         : Dicky Zulkarnain
 * Date           : 31/10/22
 * Original File  : BaseViewModel
 ***/

package com.dickiez.movee.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dickiez.data.DataResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {

  private val _errorMessage = MutableSharedFlow<String>()
  private val _loading = MutableStateFlow(false)

  val loading get() = _loading.asStateFlow()
  val errorMessage get() = _errorMessage.asSharedFlow()

  fun showErrorMessage(message : String?) = viewModelScope.launch {
    _errorMessage.emit(message ?: DataResult.DEFAULT_ERROR_MESSAGE)
  }

  fun setLoading(value: Boolean) {
    _loading.value = value
  }

}