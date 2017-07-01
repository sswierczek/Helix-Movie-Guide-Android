package com.androidmess.helix.discovery.usecase

import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.model.repository.Repository
import io.reactivex.Observable

/**
 * Use case for providing movies discovery data.
 */
class GetDiscoveryMoviesUseCase(val repository: Repository.Discover) {

    fun execute(page: Int): Observable<MovieResult> {
        return repository.discoverMovies(page)
    }
}