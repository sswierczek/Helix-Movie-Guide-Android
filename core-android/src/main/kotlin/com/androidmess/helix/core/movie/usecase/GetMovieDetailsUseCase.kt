package com.androidmess.helix.core.movie.usecase

import com.androidmess.helix.core.data.models.MovieDetails
import com.androidmess.helix.core.data.models.Response
import com.androidmess.helix.core.data.repository.Repository

class GetMovieDetailsUseCase(val repository: Repository.Details) {

    suspend fun execute(id: Int): Response<MovieDetails> {
        return repository.fetch(id)
    }
}