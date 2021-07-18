package com.androidmess.helix.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidmess.helix.data.models.Movie
import com.androidmess.helix.data.models.MovieDetails
import com.androidmess.helix.data.models.Response
import com.androidmess.helix.debug.L
import com.androidmess.helix.movie.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val l: L
) : ViewModel() {

    val data = MutableLiveData<MovieDetails?>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val trailerId = MutableLiveData<String>()

    fun viewReady(movie: Movie) {
        fetchDetails(movie)
    }

    private fun fetchDetails(movie: Movie) {
        viewModelScope.launch {
            progress.postValue(true)

            when (val response = getMovieDetailsUseCase.execute(movie.id)) {
                is Response.Success -> {
                    data.postValue(response.data)
                    val videos = response.data.youTubeVideosIds
                    if (videos.isNotEmpty()) {
                        trailerId.postValue(videos.first())
                    }
                }
                is Response.Error -> {
                    error.postValue(response.message ?: "")
                    l.e("Error fetching movie details, ${response.message}")
                }
            }

            progress.postValue(false)
        }
    }
}
