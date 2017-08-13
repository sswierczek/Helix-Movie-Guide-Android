package com.androidmess.helix.discover.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.androidmess.helix.R
import com.androidmess.helix.discover.model.data.DiscoverMovieViewModel
import com.androidmess.helix.discover.presentation.DiscoverPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : AppCompatActivity(), DiscoverView {

    // FIXME Add presenter persistence
    @Inject
    lateinit var presenter: DiscoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        // FIXME Add Base Activity to not call presenter methods
        presenter.connect(view = this)
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
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMovies(movies: List<DiscoverMovieViewModel>) {
        // FIXME Add RecyclerView
        fakeContainer.text = movies.toString()
    }

    override fun showError(error: Throwable?) {
        // FIXME Add error messages handling
        fakeContainer.text = error.toString()
    }
}