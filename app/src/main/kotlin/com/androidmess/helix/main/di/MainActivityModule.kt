package com.androidmess.helix.main.di

import com.androidmess.helix.main.view.MainActivity
import com.androidmess.helix.main.view.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.applicationContext

class MainActivityModule {

    fun create() = applicationContext {
        context(MainActivity.CONTEXT_NAME) {
            viewModel { MainViewModel() }
        }
    }
}