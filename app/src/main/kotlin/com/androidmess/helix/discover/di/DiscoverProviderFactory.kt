package com.androidmess.helix.discover.di;

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.discover.presentation.DiscoverPresenter
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase

class DiscoverPresenterFactory(private val schedulers: SchedulersInjector,
                               private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscoverPresenter(schedulers, getDiscoverMoviesUseCase) as T
    }
}
