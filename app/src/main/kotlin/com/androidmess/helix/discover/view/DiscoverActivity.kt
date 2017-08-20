package com.androidmess.helix.discover.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

    private lateinit var dataAdapter: DiscoverAdapter

    private lateinit var layoutManager: LinearLayoutManager

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
        layoutManager = LinearLayoutManager(this)
        dataAdapter = DiscoverAdapter()
        discoverDataContainer.setHasFixedSize(true)
        discoverDataContainer.layoutManager = layoutManager
        discoverDataContainer.adapter = dataAdapter
        discoverDataContainer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount;
                val totalItemCount = layoutManager.itemCount;
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    presenter.scrolledToBottom()
                }
            }
        })
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
        dataAdapter.addData(movies)
    }

    override fun showError(error: Throwable?) {
        discoverError.visibility = View.VISIBLE
    }
}