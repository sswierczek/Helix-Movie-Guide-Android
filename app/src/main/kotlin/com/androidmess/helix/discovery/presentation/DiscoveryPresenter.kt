package com.androidmess.helix.discovery.presentation

import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.presentation.base.BaseMvpPresenter
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.discovery.model.data.DiscoverMovieViewModel
import com.androidmess.helix.discovery.usecase.GetDiscoveryMoviesUseCase
import com.androidmess.helix.discovery.view.DiscoverView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class DiscoveryPresenter(schedulersInjector: SchedulersInjector,
                         getDiscoveryMoviesUseCase: GetDiscoveryMoviesUseCase)
    : BaseMvpPresenter<DiscoverView>(schedulersInjector) {

    // FIXME Find a way to move it to constructor and exclude from coverage reports
    val getDiscoveryMoviesUseCase: GetDiscoveryMoviesUseCase = getDiscoveryMoviesUseCase

    // FIXME add loading next pages
    var page: Int = 1
    var disposables: CompositeDisposable = CompositeDisposable()

    override fun connect(view: DiscoverView) {
        super.connect(view)
        val discoverDisposable = getDiscoveryMoviesUseCase
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