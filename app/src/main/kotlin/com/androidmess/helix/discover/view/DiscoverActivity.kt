package com.androidmess.helix.discover.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.androidmess.helix.BR
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.common.di.activity.DiActivityPlugin
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.discover.presentation.DiscoverViewModel
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import kotlinx.android.synthetic.main.activity_discover.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class DiscoverActivity : CompositeAppCompatActivity() {

    companion object {
        const val CONTEXT_NAME = "DiscoverActivityContext"
    }

    val discoverViewModel: DiscoverViewModel by viewModel()
    val dataAdapter: DiscoverAdapter by inject()
    val layoutManager: LinearLayoutManager by inject()
    val onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDI()
        setupDataBinding()
        super.onCreate(savedInstanceState)
        setupDataContainer()
        discoverViewModel.startFetchingData()
    }

    private fun setupDI() {
        registerPlugin(DiActivityPlugin(this, CONTEXT_NAME))
    }

    private fun setupDataBinding() {
        val plugin = DataBindingActivityPlugin(this, discoverViewModel, R.layout.activity_discover)
        plugin.addBinding(BR.adapter, dataAdapter)
        registerPlugin(plugin)
    }

    private fun setupDataContainer() {
        // FIXME Move to data binding
        onScrolledToBottomDetector
            .scrollEvents(discoverDataContainer.scrollEvents())
            .observe()
            .subscribe {
                discoverViewModel.scroll.notifyChange()
            }
        discoverDataContainer.setHasFixedSize(true)
        discoverDataContainer.layoutManager = layoutManager
        discoverDataContainer.adapter = dataAdapter
    }
}