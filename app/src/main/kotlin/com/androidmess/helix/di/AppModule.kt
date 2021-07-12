package com.androidmess.helix.di

import com.androidmess.helix.common.app.AppConfig
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.debug.TimberL
import com.androidmess.helix.common.navigation.Navigator
import com.androidmess.helix.common.rx.AppSchedulersInjector
import com.androidmess.helix.common.rx.SchedulersInjector
import org.koin.dsl.module

class AppModule {
    fun create() = module {
        single { AppConfig() }
        single<L> { TimberL(get()) }
        single<SchedulersInjector> { AppSchedulersInjector() }
        single { Navigator() }
    }
}
