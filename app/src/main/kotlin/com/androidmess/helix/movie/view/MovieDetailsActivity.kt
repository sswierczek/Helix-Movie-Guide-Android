package com.androidmess.helix.movie.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.databinding.ActivityMovieDetailsBinding
import com.androidmess.helix.movie.view.data.MovieViewData
import com.androidmess.helix.movie.viewmodel.MovieDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsActivity : CompositeAppCompatActivity() {

    private val detailsViewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        movieFromIntent()?.let {
            setupDataBinding(movie = it)
        }
        super.onCreate(savedInstanceState)
        movieFromIntent()?.let {
            detailsViewModel.startFetchingMovie(it)
        }

        // FIXME temp display
        detailsViewModel.data.subscribe {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupDataBinding(movie: MovieViewData) {
        registerPlugin(
            DataBindingActivityPlugin<ActivityMovieDetailsBinding>(
                this,
                detailsViewModel,
                R.layout.activity_movie_details
            ) {
                it.movie = movie
            })
    }

    private fun movieFromIntent() =
        intent?.extras?.getParcelable<MovieViewData>(getString(R.string.movieDetailsArg))
}


fun Activity.movieDetailsIntent(movieViewData: MovieViewData): Intent {
    return Intent(this, MovieDetailsActivity::class.java).apply {
        putExtra(getString(R.string.movieDetailsArg), movieViewData)
    }
}