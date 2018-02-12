package com.androidmess.helix.discover.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.databinding.ActivityDiscoverBinding
import com.androidmess.helix.discover.presentation.DiscoverPresenter
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : CompositeAppCompatActivity() {

    @Inject
    lateinit var presenter: DiscoverPresenter

    @Inject
    lateinit var dataAdapter: DiscoverAdapter

    @Inject
    lateinit var layoutManager: GridLayoutManager

    @Inject
    lateinit var onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val binding: ActivityDiscoverBinding = DataBindingUtil.setContentView(this, R.layout.activity_discover)
        binding.presenter = presenter
        setupDataContainer()

        presenter.data.subscribe({
            dataAdapter.addData(it)
        })
        presenter.startFetchingData()
    }

    private fun setupDataContainer() {
        // FIXME Move to data binding
        onScrolledToBottomDetector
                .scrollEvents(discoverDataContainer.scrollEvents())
                .observe()
                .subscribe {
                    presenter.scroll.notifyChange()
                }
        discoverDataContainer.setHasFixedSize(true)
        discoverDataContainer.layoutManager = layoutManager
        discoverDataContainer.adapter = dataAdapter
    }
}