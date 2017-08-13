package com.androidmess.helix.discover.di

import com.androidmess.helix.discover.view.DiscoverActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface DiscoverActivitySubcomponent : AndroidInjector<DiscoverActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DiscoverActivity>()
}