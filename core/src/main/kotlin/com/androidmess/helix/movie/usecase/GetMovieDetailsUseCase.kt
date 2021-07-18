package com.androidmess.helix.movie.usecase

import com.androidmess.helix.data.models.MovieDetails
import com.androidmess.helix.data.models.Response
import com.androidmess.helix.data.repository.Repository

class GetMovieDetailsUseCase(val repository: Repository.Details) {

    suspend fun execute(id: Int): Response<MovieDetails> {
        return repository.fetch(id)
    }
}