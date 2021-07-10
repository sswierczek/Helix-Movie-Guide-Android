package com.androidmess.helix.discover.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidmess.helix.common.debug.e
import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.movie.view.data.MovieViewData
import com.androidmess.helix.movie.view.data.viewData
import io.reactivex.disposables.CompositeDisposable

class DiscoverViewModel(
    private val schedulers: SchedulersInjector,
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
) : ViewModel() {

    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val data = MutableLiveData<List<MovieViewData>>()

    private var isLoading: Boolean = false // FIXME Introduce state
    private var page: Int = 1
    private var disposables: CompositeDisposable = CompositeDisposable()

    fun viewReady() {
        fetchPage(page)
    }

    fun onLoadNextData() {
        loadNextData()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun loadNextData() {
        if (isLoading) {
            return
        }
        page += 1
        fetchPage(page)
    }

    private fun fetchPage(page: Int) {
        isLoading = true
        val discoverDisposable = getDiscoverMoviesUseCase
            .execute(page)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .flatMapIterable(MovieResult::results)
            .map { it.viewData() }
            .toList()
            .doOnSubscribe { progress.postValue(true) }
            .doFinally { progress.postValue(false) }
            .subscribe({
                data.postValue(it)
                isLoading = false
            }, {
                e(it)
                error.postValue(true)
            })
        disposables.add(discoverDisposable)
    }
}
