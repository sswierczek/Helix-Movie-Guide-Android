package com.androidmess.helix.discover.model.di

import com.androidmess.helix.core.data.repository.Repository
import com.androidmess.helix.discover.model.repository.RetrofitDiscoverRepository
import com.androidmess.helix.core.discover.usecase.GetDiscoverMoviesUseCase
import org.koin.dsl.module

class DiscoverModelModule {
    fun create() = module {
        single<Repository.Discover> { RetrofitDiscoverRepository(get(), get()) }
        single { GetDiscoverMoviesUseCase(get()) }
    }
}
