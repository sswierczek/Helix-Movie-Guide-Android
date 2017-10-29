package com.androidmess.helix.discover.model.di

import com.androidmess.helix.BuildConfig
import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.discover.model.repository.RetrofitDiscoverRepository
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DiscoverModelModule {

    @Provides
    @Singleton
    fun providesRepositoryDiscover(retrofit: Retrofit): Repository.Discover {
        return RetrofitDiscoverRepository(retrofit, BuildConfig.API_KEY) // FIXME Add config
    }

    @Provides
    @Singleton
    fun provideGetDiscoverMoviesUseCase(repositoryDiscover: Repository.Discover): GetDiscoverMoviesUseCase {
        return GetDiscoverMoviesUseCase(repositoryDiscover)
    }
}