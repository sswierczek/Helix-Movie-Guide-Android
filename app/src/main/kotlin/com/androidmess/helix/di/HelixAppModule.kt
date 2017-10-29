package com.androidmess.helix.di

import com.androidmess.helix.common.rx.AppSchedulersInjector
import com.androidmess.helix.common.rx.SchedulersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelixAppModule {

    @Provides
    @Singleton
    fun provideSchedulerInjector(): SchedulersInjector {
        return AppSchedulersInjector()
    }
    
}