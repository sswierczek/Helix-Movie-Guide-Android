package com.androidmess.helix

import android.app.Application
import androidx.multidex.MultiDex
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.network.di.NetworkModule
import com.androidmess.helix.di.AppModule
import com.androidmess.helix.discover.di.DiscoverFragmentModule
import com.androidmess.helix.discover.model.di.DiscoverModelModule
import com.androidmess.helix.main.di.MainActivityModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HelixApp : Application() {

    val l: L by inject()
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidLogger()
            androidContext(this@HelixApp)
            modules(
                    listOf(
                            AppModule().create(),
                            NetworkModule().create(),
                            MainActivityModule().create(),
                            DiscoverModelModule().create(),
                            DiscoverFragmentModule().create())
            )
        }
        initLibraries()
    }

    private fun initLibraries() {
        l.init()
    }
}
