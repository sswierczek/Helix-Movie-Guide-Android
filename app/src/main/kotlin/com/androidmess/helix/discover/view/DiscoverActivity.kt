package com.androidmess.helix.discover.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.debug.L
import com.androidmess.helix.common.mvp.plugin.PresenterCompositeActivityPlugin
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.common.ui.view.show
import com.androidmess.helix.discover.model.data.DiscoverMovieViewModel
import com.androidmess.helix.discover.presentation.DiscoverPresenter
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : CompositeAppCompatActivity(), DiscoverView {

    @Inject
    lateinit var presenter: DiscoverPresenter

    @Inject
    lateinit var dataAdapter: DiscoverAdapter

    @Inject
    lateinit var layoutManager: GridLayoutManager

    @Inject
    lateinit var onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector

    @Inject
    lateinit var l: L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        registerPlugin(PresenterCompositeActivityPlugin(this, presenter))
        setContentView(R.layout.activity_discover)
        setupDataContainer()
    }

    private fun setupDataContainer() {
        onScrolledToBottomDetector
                .scrollEvents(discoverDataContainer.scrollEvents())
                .observe()
                .subscribe {
                    l.d("On scrolled to bottom...")
                    presenter.scrolledToBottom()
                }
        discoverDataContainer.setHasFixedSize(true)
        discoverDataContainer.layoutManager = layoutManager
        discoverDataContainer.adapter = dataAdapter
    }

    override fun showLoading(show: Boolean) {
        discoverProgress.show(isVisible = show)
    }

    override fun showMovies(movies: List<DiscoverMovieViewModel>) {
        dataAdapter.addData(movies)
    }

    override fun showError(error: Throwable?) {
        discoverError.visibility = View.VISIBLE
    }
}