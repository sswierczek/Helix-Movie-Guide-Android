package com.androidmess.helix

import android.app.Application
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.network.di.networkModule
import com.androidmess.helix.di.appModule
import com.androidmess.helix.discover.di.discoverActivityModule
import com.androidmess.helix.discover.model.di.discoverModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class HelixApp : Application() {

    val l: L by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                appModule,
                networkModule,
                discoverModelModule,
                discoverActivityModule
            )
        )
        initLibraries()
    }

    private fun initLibraries() {
        l.init()
    }
}