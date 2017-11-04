package com.androidmess.helix.di

import android.app.Application
import android.content.Context
import com.androidmess.helix.common.app.AppConfig
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.debug.TimberL
import com.androidmess.helix.common.rx.AppSchedulersInjector
import com.androidmess.helix.common.rx.SchedulersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelixAppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideAppConfig(): AppConfig {
        return AppConfig()
    }

    @Provides
    @Singleton
    fun provideL(appConfig: AppConfig): L {
        return TimberL(appConfig)
    }

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