package com.androidmess.helix.discover.presentation

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.androidmess.helix.common.databinding.extensions.addOnPropertyChanged
import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.discover.model.data.DiscoverMovieViewModel
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


class DiscoverViewModel(private val schedulers: SchedulersInjector,
                        private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase) : ViewModel() {

    val scroll = ObservableBoolean()

    val progress = ObservableBoolean()

    val error = ObservableBoolean()

    val data = PublishSubject.create<List<DiscoverMovieViewModel>>()

    private var isLoading: Boolean = false // FIXME Introduce state
    private var page: Int = 1
    private var disposables: CompositeDisposable = CompositeDisposable()

    init {
        disposables.add(scroll.addOnPropertyChanged { scrolledToBottom() })
    }

    fun startFetchingData() {
        fetchPage(page)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun scrolledToBottom() {
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
                .flatMapIterable(MovieResult::results)
                .flatMap { Observable.just(DiscoverMovieViewModel.Mapper.fromMovie(it)) }
                .toList()
                .observeOn(schedulers.ui())
                .doOnSubscribe { progress.set(true) }
                .doFinally { progress.set(false) }
                .subscribe({
                    isLoading = false
                    data.onNext(it)
                }, { error.set(true) })
        disposables.add(discoverDisposable)
    }
}