package com.androidmess.helix.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.debug.e
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.movie.model.data.MovieDetailsData
import com.androidmess.helix.movie.usecase.GetMovieDetailsUseCase
import com.androidmess.helix.movie.view.data.MovieViewData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class MovieDetailsViewModel(
    private val schedulers: SchedulersInjector,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val l: L
) : ViewModel() {

    val data: PublishSubject<MovieDetailsData> = PublishSubject.create()

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun startFetchingMovie(movie: MovieViewData) {
        val detailsFetchDisposable = getMovieDetailsUseCase
            .execute(movie.id)
            .subscribeOn(schedulers.io())
//                .flatMap { Observable.just(MovieViewData.Mapper.fromMovie(it)) } // FIXME map to view data
            .observeOn(schedulers.ui())
            .doOnSubscribe {
                // TODO
            }
            .doFinally {
                // TODO
            }
            .subscribe({
                data.onNext(it)
            }, {
                l.e("Error fetching movie details, " + it.message)
            }, {
                // TODO
            })
        disposables.add(detailsFetchDisposable)
    }
}