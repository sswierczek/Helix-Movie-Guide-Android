package com.androidmess.helix.movie.view.di

import com.androidmess.helix.movie.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MovieDetailsModule {
    fun create() = module {
        viewModel { MovieDetailsViewModel(get(), get()) }
    }
}