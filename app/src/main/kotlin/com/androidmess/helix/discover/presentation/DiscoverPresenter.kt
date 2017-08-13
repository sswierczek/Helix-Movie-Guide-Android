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
    val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase = getDiscoverMoviesUseCase

    // FIXME add loading next pages
    var page: Int = 1
    var disposables: CompositeDisposable = CompositeDisposable()

    override fun connect(view: DiscoverView) {
        super.connect(view)
        val discoverDisposable = getDiscoverMoviesUseCase
                .execute(page)
                .observeOn(schedulersInjector.ui())
                .doOnSubscribe { view.showLoading(true) }
                .doFinally { view.showLoading(false) }
                .flatMapIterable(MovieResult::results)
                .flatMap { Observable.just(DiscoverMovieViewModel.Mapper.fromMovie(it)) }
                .toList()
                .subscribeOn(schedulersInjector.io())
                .subscribe({ view.showMovies(it) },
                        { view.showError(it) })
        disposables.add(discoverDisposable)
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
}