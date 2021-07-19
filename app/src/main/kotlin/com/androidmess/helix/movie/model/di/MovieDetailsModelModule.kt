package com.androidmess.helix.movie.model.di

import com.androidmess.helix.core.data.repository.Repository
import com.androidmess.helix.movie.model.repository.RetrofitDetailsRepository
import com.androidmess.helix.core.movie.usecase.GetMovieDetailsUseCase
import org.koin.dsl.module

class MovieDetailsModelModule {

    fun create() = module {
        single<Repository.Details> { RetrofitDetailsRepository(get(), get()) }
        single { GetMovieDetailsUseCase(get()) }
    }
}