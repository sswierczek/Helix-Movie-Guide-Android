package com.androidmess.helix.discover.di

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.androidmess.helix.R
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.discover.view.DiscoverAdapter
import com.androidmess.helix.discover.view.DiscoverFragment
import com.androidmess.helix.discover.view.DiscoverViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

class DiscoverFragmentModule {
    fun create() = applicationContext {
        context(DiscoverFragment.CONTEXT_NAME) {
            viewModel { DiscoverViewModel(get(), get()) }

            bean { RecyclerViewOnScrolledToBottomDetector(get(), get()) }
            bean { DiscoverLayoutManagerFactory(get()).create() as LinearLayoutManager }
            bean { DiscoverAdapter() }
        }
    }

    private class DiscoverLayoutManagerFactory(val context: Context) {

        fun create(): GridLayoutManager {
            return GridLayoutManager(
                    context,
                    context.resources.getInteger(R.integer.discover_view_span_count)
            )
        }
    }
}
