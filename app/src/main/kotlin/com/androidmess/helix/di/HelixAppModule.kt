package com.androidmess.helix.di

import android.app.Application
import android.content.Context
import com.androidmess.helix.common.rx.AppSchedulersInjector
import com.androidmess.helix.common.rx.SchedulersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelixAppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideSchedulerInjector(): SchedulersInjector {
        return AppSchedulersInjector()
    }
}