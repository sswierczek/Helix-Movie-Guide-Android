package com.androidmess.helix.discover.di

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.androidmess.helix.R
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.common.ui.RecyclerViewItemSizeCalculator
import com.androidmess.helix.common.ui.getScreenWidth
import com.androidmess.helix.di.scopes.ActivityScope
import com.androidmess.helix.discover.presentation.DiscoverPresenter
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.discover.view.DiscoverAdapter
import dagger.Module
import dagger.Provides

@Module
class DiscoverActivityModule {

    @ActivityScope
    @Provides
    fun providesDiscoverPresenter(schedulersInjector: SchedulersInjector,
                                  getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase): DiscoverPresenter {
        return DiscoverPresenter(schedulersInjector, getDiscoverMoviesUseCase)
    }

    @ActivityScope
    @Provides
    fun provideRecyclerViewItemSizeCalculator(context: Context): RecyclerViewItemSizeCalculator {
        return RecyclerViewItemSizeCalculator(context.getScreenWidth())
    }

    @ActivityScope
    @Provides
    fun provideAdapter(context: Context,
                       itemSizeCalculator: RecyclerViewItemSizeCalculator): DiscoverAdapter {
        itemSizeCalculator.spanCount = context.resources.getInteger(R.integer.discover_view_span_count)
        return DiscoverAdapter(itemSizeCalculator)
    }

    @ActivityScope
    @Provides
    fun provideLayoutManager(context: Context): GridLayoutManager {
        return GridLayoutManager(context, context.resources.getInteger(R.integer.discover_view_span_count))
    }
}