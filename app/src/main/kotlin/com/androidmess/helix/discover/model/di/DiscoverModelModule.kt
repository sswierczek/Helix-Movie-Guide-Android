package com.androidmess.helix.discover.model.di

import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.discover.model.repository.RetrofitDiscoverRepository
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import org.koin.dsl.module

class DiscoverModelModule {
    fun create() = module {
        single<Repository.Discover> { RetrofitDiscoverRepository(get(), get()) }
        single { GetDiscoverMoviesUseCase(get()) }
    }
}
