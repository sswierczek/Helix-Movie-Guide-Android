package com.androidmess.helix.discover.di

import android.app.Activity
import com.androidmess.helix.discover.view.DiscoverActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(DiscoverActivityComponent::class))
internal abstract class DiscoverActivityBinder {

    @Binds
    @IntoMap
    @ActivityKey(DiscoverActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: DiscoverActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>
}