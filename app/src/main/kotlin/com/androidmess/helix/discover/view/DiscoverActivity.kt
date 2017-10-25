package com.androidmess.helix.discover.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.androidmess.helix.R
import com.androidmess.helix.common.ui.show
import com.androidmess.helix.discover.model.data.DiscoverMovieViewModel
import com.androidmess.helix.discover.presentation.DiscoverPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : AppCompatActivity(), DiscoverView {

    // FIXME Add presenter persistence
    @Inject
    lateinit var presenter: DiscoverPresenter

    lateinit var discoverDataAdapter: DiscoverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        setupDataContainer()

        // FIXME Add Base Activity to not call presenter methods
        presenter.connect(view = this)
    }

    private fun setupDataContainer() {
        // FIXME Add tablet support
        discoverDataContainer.setHasFixedSize(true)
        discoverDataContainer.layoutManager = GridLayoutManager(this, 2)
        discoverDataAdapter = DiscoverAdapter()
        discoverDataContainer.adapter = discoverDataAdapter
    }

    override fun onStart() {
        super.onStart()
        // FIXME Add Base Activity to not call presenter methods
        presenter.visible()
    }

    override fun onStop() {
        super.onStop()
        // FIXME Add Base Activity to not call presenter methods
        presenter.invisible()
        if (isFinishing) {
            presenter.disconnect()
        }
    }

    override fun showLoading(show: Boolean) {
        discoverProgress.show(isVisible = show)
    }

    override fun showMovies(movies: List<DiscoverMovieViewModel>) {
        discoverDataAdapter.replaceData(movies)
    }

    override fun showError(error: Throwable?) {
        discoverError.visibility = View.VISIBLE
    }
}