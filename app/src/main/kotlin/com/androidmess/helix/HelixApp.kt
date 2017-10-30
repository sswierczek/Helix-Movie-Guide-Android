package com.androidmess.helix

import android.app.Activity
import android.app.Application
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.di.DaggerHelixAppComponent
import com.androidmess.helix.di.HelixAppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class HelixApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var l: L

    override fun onCreate() {
        super.onCreate()
        DaggerHelixAppComponent.builder()
                .helixAppModule(HelixAppModule(this))
                .build()
                .inject(this)
        initLibraries()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    private fun initLibraries() {
        l.init()
    }
}