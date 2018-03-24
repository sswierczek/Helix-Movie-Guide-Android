package com.androidmess.helix

import android.app.Application
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.network.di.NetworkModule
import com.androidmess.helix.di.AppModule
import com.androidmess.helix.discover.di.DiscoverActivityModule
import com.androidmess.helix.discover.model.di.DiscoverModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class HelixApp : Application() {

    val l: L by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                AppModule().create(),
                NetworkModule().create(),
                DiscoverModelModule().create(),
                DiscoverActivityModule().create()
            )
        )
        initLibraries()
    }

    private fun initLibraries() {
        l.init()
    }
}