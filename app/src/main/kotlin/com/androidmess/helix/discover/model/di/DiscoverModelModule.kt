package com.androidmess.helix.discover.model.di

import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.discover.model.repository.RetrofitDiscoverRepository
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import org.koin.dsl.module.applicationContext

val discoverModelModule = applicationContext {
    bean { RetrofitDiscoverRepository(get(), get()) as Repository.Discover }
    bean { GetDiscoverMoviesUseCase(get()) }
}