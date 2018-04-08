package com.androidmess.helix.discover.usecase

import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.model.repository.Repository
import io.reactivex.Observable

/**
 * Use case for providing movies discover data.
 */
class GetDiscoverMoviesUseCase(val repository: Repository.Discover) {

    fun execute(page: Int): Observable<MovieResult> {
        return repository.discoverMovies(page)
    }
}
