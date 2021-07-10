package com.androidmess.helix.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.debug.e
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.movie.usecase.GetMovieDetailsUseCase
import com.androidmess.helix.movie.view.data.MovieDetailsViewData
import com.androidmess.helix.movie.view.data.MovieViewData
import com.androidmess.helix.movie.view.data.viewData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class MovieDetailsViewModel(
    private val schedulers: SchedulersInjector,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val l: L
) : ViewModel() {

    val data = MutableLiveData<MovieDetailsViewData>()
    val showProgress = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()

    private var disposables: CompositeDisposable = CompositeDisposable()

    fun viewReady(movie: MovieViewData) {
        fetchDetails(movie)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun fetchDetails(movie: MovieViewData) {
        disposables += getMovieDetailsUseCase
            .execute(movie.id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map { it.viewData() }
            .doOnSubscribe { showProgress.postValue(true) }
            .doFinally { showProgress.postValue(false) }
            .subscribe({ data.postValue(it) }, {
                showError.postValue(it.message) // FIXME Add mapping to R.string
                l.e("Error fetching movie details, " + it.message)
            })
    }
}
