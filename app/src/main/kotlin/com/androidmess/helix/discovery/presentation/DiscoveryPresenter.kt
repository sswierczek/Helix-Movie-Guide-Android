package com.androidmess.helix.discovery.presentation

import com.androidmess.helix.common.presentation.base.BaseMvpPresenter
import com.androidmess.helix.discovery.usecase.GetDiscoveryMoviesUseCase
import com.androidmess.helix.discovery.view.DiscoverView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DiscoveryPresenter(val getDiscoveryMoviesUseCase: GetDiscoveryMoviesUseCase)
    : BaseMvpPresenter<DiscoverView>() {

    // FIXME add loading next pages
    var page: Int = 1
    var disposables: CompositeDisposable = CompositeDisposable()

    override fun connect(view: DiscoverView) {
        super.connect(view)
        val discoverDisposable = getDiscoveryMoviesUseCase
                .execute(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { view.showLoading(true) }
                .doFinally { view.showLoading(false) }
                .subscribe(
                        { view.showMovies(it) }, // FIXME Add ViewModel mapping
                        { view.showError(it) })
        disposables.add(discoverDisposable)
    }

    override fun visible() {
        // nothing now
    }

    override fun invisible() {
        // nothing now
    }

    override fun disconnect() {
        super.disconnect()
        disposables.clear()
    }
}