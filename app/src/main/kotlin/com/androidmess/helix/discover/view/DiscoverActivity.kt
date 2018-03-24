package com.androidmess.helix.discover.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.androidmess.helix.BR
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.discover.presentation.DiscoverViewModel
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : CompositeAppCompatActivity() {

    @Inject
    lateinit var viewModel: DiscoverViewModel

    @Inject
    lateinit var dataAdapter: DiscoverAdapter

    @Inject
    lateinit var layoutManager: GridLayoutManager

    @Inject
    lateinit var onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setupDataBinding()
        super.onCreate(savedInstanceState)
        setupDataContainer()
        viewModel.startFetchingData()
    }

    private fun setupDataBinding() {
        val plugin = DataBindingActivityPlugin(this, viewModel, R.layout.activity_discover)
        plugin.addBinding(BR.adapter, dataAdapter)
        registerPlugin(plugin)
    }

    private fun setupDataContainer() {
        // FIXME Move to data binding
        onScrolledToBottomDetector
            .scrollEvents(discoverDataContainer.scrollEvents())
            .observe()
            .subscribe {
                viewModel.scroll.notifyChange()
            }
        discoverDataContainer.setHasFixedSize(true)
        discoverDataContainer.layoutManager = layoutManager
        discoverDataContainer.adapter = dataAdapter
    }
}