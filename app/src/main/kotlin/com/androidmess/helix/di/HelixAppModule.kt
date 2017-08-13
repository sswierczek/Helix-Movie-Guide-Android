package com.androidmess.helix.di

import com.androidmess.helix.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelixAppModule {

    @Provides
    @Singleton
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }
}