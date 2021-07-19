package com.androidmess.helix.core.discover.usecase

import com.androidmess.helix.core.data.models.Movie
import com.androidmess.helix.core.data.models.Response
import com.androidmess.helix.core.data.repository.Repository

/**
 * Use case for providing movies discover data.
 */
class GetDiscoverMoviesUseCase(val repository: Repository.Discover) {

    suspend fun execute(page: Int): Response<List<Movie>> {
        return repository.discover(page)
    }
}
