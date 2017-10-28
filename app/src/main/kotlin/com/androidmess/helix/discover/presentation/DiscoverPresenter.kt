package com.androidmess.helix.discover.presentation

import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.presentation.base.BaseMvpPresenter
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.discover.model.data.DiscoverMovieViewModel
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.discover.view.DiscoverView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class DiscoverPresenter(schedulersInjector: SchedulersInjector,
                        getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase)
    : BaseMvpPresenter<DiscoverView>(schedulersInjector) {

    // FIXME Find a way to move it to constructor and exclude from coverage reports
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase = getDiscoverMoviesUseCase
    private var isLoading: Boolean = false // FIXME Introduce state
    private var page: Int = 1
    private var disposables: CompositeDisposable = CompositeDisposable()

    fun scrolledToBottom() {
        if (isLoading) {
            return
        }
        page += 1
        fetchPage(page)
    }

    override fun connect(view: DiscoverView) {
        super.connect(view)
        page = 1
        fetchPage(page)
    }

    override fun visible() {
        // nothing
    }

    override fun invisible() {
        // nothing
    }

    override fun disconnect() {
        super.disconnect()
        disposables.clear()
    }

    private fun fetchPage(page: Int) {
        isLoading = true
        val discoverDisposable = getDiscoverMoviesUseCase
                .execute(page)
                .subscribeOn(schedulersInjector.io())
                .flatMapIterable(MovieResult::results)
                .flatMap { Observable.just(DiscoverMovieViewModel.Mapper.fromMovie(it)) }
                .toList()
                .observeOn(schedulersInjector.ui())
                .doOnSubscribe { getView()?.showLoading(true) }
                .doFinally { getView()?.showLoading(false) }
                .subscribe({
                    isLoading = false
                    getView()?.showMovies(it)
                }, { getView()?.showError(it) })
        disposables.add(discoverDisposable)
    }
}