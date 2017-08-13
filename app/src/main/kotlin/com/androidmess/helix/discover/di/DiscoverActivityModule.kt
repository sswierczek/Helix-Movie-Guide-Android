package com.androidmess.helix.discover.di

import android.app.Activity
import com.androidmess.helix.discover.view.DiscoverActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(DiscoverActivitySubcomponent::class))
internal abstract class DiscoverActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(DiscoverActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: DiscoverActivitySubcomponent.Builder):
            AndroidInjector.Factory<out Activity>
}