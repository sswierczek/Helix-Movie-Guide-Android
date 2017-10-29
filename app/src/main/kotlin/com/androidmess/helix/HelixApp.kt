package com.androidmess.helix

import android.app.Activity
import android.app.Application
import com.androidmess.helix.di.DaggerHelixAppComponent
import com.androidmess.helix.di.HelixAppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class HelixApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerHelixAppComponent.builder()
                .helixAppModule(HelixAppModule(this))
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}