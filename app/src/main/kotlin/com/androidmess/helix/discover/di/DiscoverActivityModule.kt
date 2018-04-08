package com.androidmess.helix.discover.di

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.androidmess.helix.R
import com.androidmess.helix.common.app.getScreenWidth
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewItemSizeCalculator
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.discover.presentation.DiscoverViewModel
import com.androidmess.helix.discover.view.DiscoverActivity
import com.androidmess.helix.discover.view.DiscoverAdapter
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

class DiscoverActivityModule {
    fun create() = applicationContext {
        context(DiscoverActivity.CONTEXT_NAME) {
            viewModel { DiscoverViewModel(get(), get()) }

            bean { RecyclerViewOnScrolledToBottomDetector(get(), get()) }
            bean { DiscoverLayoutManagerFactory(get()).create() as LinearLayoutManager }
            bean { RecyclerViewItemSizeCalculatorFactory(get()).create() }
            bean { DiscoverAdapter(get()) }
        }
    }

    private class RecyclerViewItemSizeCalculatorFactory(val context: Context) {

        fun create(): RecyclerViewItemSizeCalculator {
            val calculator = RecyclerViewItemSizeCalculator(context.getScreenWidth())
            calculator.spanCount = context.resources.getInteger(R.integer.discover_view_span_count)
            return calculator
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
