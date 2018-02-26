package com.androidmess.helix.movie.model.di

import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.movie.model.repository.RetrofitMovieDetailsRepository
import com.androidmess.helix.movie.usecase.GetMovieDetailsUseCase
import org.koin.dsl.module

class MovieDetailsModelModule {

    fun create() = module {
        single<Repository.MovieDetails> { RetrofitMovieDetailsRepository(get(), get()) }
        single { GetMovieDetailsUseCase(get()) }
    }
}