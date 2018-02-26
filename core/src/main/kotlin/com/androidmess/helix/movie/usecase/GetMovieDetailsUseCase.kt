package com.androidmess.helix.movie.usecase

import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.movie.model.data.MovieDetailsData
import io.reactivex.Observable

class GetMovieDetailsUseCase(val repository: Repository.MovieDetails) {

    fun execute(id: Int): Observable<MovieDetailsData> {
        return repository.fetchMovieDetails(id)
    }
}