package com.androidmess.helix.di

import com.androidmess.helix.common.app.AppConfig
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.debug.TimberL
import com.androidmess.helix.common.rx.AppSchedulersInjector
import com.androidmess.helix.common.rx.SchedulersInjector
import org.koin.dsl.module.applicationContext

class AppModule {
    fun create() = applicationContext {
        bean { AppConfig() }
        bean { TimberL(get()) as L }
        bean { AppSchedulersInjector() as SchedulersInjector }
    }
}
