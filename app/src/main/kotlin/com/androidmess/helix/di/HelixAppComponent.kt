package com.androidmess.helix.di

import com.androidmess.helix.HelixApp
import com.androidmess.helix.discover.di.DiscoverActivityBinder
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        HelixAppModule::class,
        DiscoverActivityBinder::class
))
interface HelixAppComponent {

    fun inject(application: HelixApp): HelixApp
}