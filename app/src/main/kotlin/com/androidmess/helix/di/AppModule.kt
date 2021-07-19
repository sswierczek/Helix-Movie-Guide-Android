package com.androidmess.helix.di

import com.androidmess.helix.common.app.AppConfig
import com.androidmess.helix.common.navigation.Navigator
import com.androidmess.helix.core.debug.L
import com.androidmess.helix.debug.TimberL
import org.koin.dsl.module

class AppModule {
    fun create() = module {
        single { AppConfig() }
        single<L> { TimberL(get()) }
        single { Navigator() }
    }
}
