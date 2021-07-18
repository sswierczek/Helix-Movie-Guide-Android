package com.androidmess.helix.discover.view.di

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidmess.helix.R
import com.androidmess.helix.discover.view.DiscoverAdapter
import com.androidmess.helix.discover.view.DiscoverFragment
import com.androidmess.helix.discover.view.DiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class DiscoverFragmentModule {
    fun create() = module {
        viewModel { DiscoverViewModel(get()) }

        scope(named<DiscoverFragment>()) {
//            scoped { RecyclerViewOnScrolledToBottomDetector(get(), get()) }
            scoped<LinearLayoutManager> { DiscoverLayoutManagerFactory(get()).create() }
            scoped { DiscoverAdapter(get()) }
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
