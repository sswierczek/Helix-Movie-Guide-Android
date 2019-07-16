package com.androidmess.helix.main.di

import com.androidmess.helix.main.view.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainActivityModule {

    fun create() = module {
        viewModel { MainViewModel() }
    }
}