package com.androidmess.helix.discover.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidmess.helix.core.data.models.Movie
import com.androidmess.helix.core.data.models.Response
import com.androidmess.helix.core.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.debug.e
import kotlinx.coroutines.launch

class DiscoverViewModel(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
) : ViewModel() {

    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val data = MutableLiveData<List<Movie>>()

    private var isLoading: Boolean = false // FIXME Introduce state
    private var page: Int = 1

    fun viewReady() {
        fetchPage(page)
    }

    fun onLoadNextData() {
        loadNextData()
    }

    private fun loadNextData() {
        if (isLoading) {
            return
        }
        page += 1
        fetchPage(page)
    }

    private fun fetchPage(page: Int) {
        viewModelScope.launch {
            isLoading = true
            progress.postValue(true)

            when (val response = getDiscoverMoviesUseCase.execute(page)) {
                is Response.Success -> {
                    val newData = data.value.orEmpty() + response.data
                    data.postValue(newData)
                }
                is Response.Error -> {
                    error.postValue(true)
                    e(response.message ?: "")
                }
            }

            progress.postValue(false)
            isLoading = false
        }
    }
}
