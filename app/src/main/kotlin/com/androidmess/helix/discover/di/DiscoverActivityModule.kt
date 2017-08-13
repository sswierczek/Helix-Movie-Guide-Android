package com.androidmess.helix.discover.di

import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.di.scopes.ActivityScope
import com.androidmess.helix.discover.presentation.DiscoverPresenter
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class DiscoverActivityModule {

    @ActivityScope
    @Provides
    fun providesDiscoverPresenter(schedulersInjector: SchedulersInjector,
                                  getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase): DiscoverPresenter {
        return DiscoverPresenter(schedulersInjector, getDiscoverMoviesUseCase)
    }
}