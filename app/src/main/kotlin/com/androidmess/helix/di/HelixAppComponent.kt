package com.androidmess.helix.di

import com.androidmess.helix.HelixApp
import com.androidmess.helix.discover.di.DiscoverActivityModule
import dagger.Component

@Component(modules = arrayOf(
        HelixAppModule::class,
        DiscoverActivityModule::class
))
interface HelixAppComponent {
    fun inject(application: HelixApp): HelixApp
}