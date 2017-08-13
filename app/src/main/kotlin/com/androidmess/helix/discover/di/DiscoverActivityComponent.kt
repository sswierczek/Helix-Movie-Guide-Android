package com.androidmess.helix.discover.di

import com.androidmess.helix.di.scopes.ActivityScope
import com.androidmess.helix.discover.view.DiscoverActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = arrayOf(
                DiscoverActivityModule::class
        )
)
interface DiscoverActivityComponent : AndroidInjector<DiscoverActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DiscoverActivity>()
}