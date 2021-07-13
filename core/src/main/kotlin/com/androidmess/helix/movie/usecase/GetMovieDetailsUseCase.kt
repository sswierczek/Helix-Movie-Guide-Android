package com.androidmess.helix.movie.usecase

import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.movie.model.data.MovieDetailsCombined
import io.reactivex.Observable

class GetMovieDetailsUseCase(val repository: Repository.MovieDetails) {

    fun execute(id: Int): Observable<MovieDetailsCombined> {
        return repository.fetchMovieDetailsCombined(id)
    }
}