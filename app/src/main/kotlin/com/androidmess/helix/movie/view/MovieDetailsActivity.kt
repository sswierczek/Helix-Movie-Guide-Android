package com.androidmess.helix.movie.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.databinding.ActivityMovieDetailsBinding
import com.androidmess.helix.movie.view.data.MovieViewData
import com.androidmess.helix.movie.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : CompositeAppCompatActivity() {

    private val detailsViewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDataBinding()
        super.onCreate(savedInstanceState)
        movieFromIntent()?.let {
            detailsViewModel.viewReady(movie = it)
        }
    }

    private fun setupDataBinding() {
        registerPlugin(
            DataBindingActivityPlugin<ActivityMovieDetailsBinding>(
                this,
                detailsViewModel,
                R.layout.activity_movie_details
            )
        )
    }

    private fun movieFromIntent() =
        intent?.extras?.getParcelable<MovieViewData>(getString(R.string.movieDetailsArg))
}

fun Activity.movieDetailsIntent(movieViewData: MovieViewData): Intent {
    return Intent(this, MovieDetailsActivity::class.java).apply {
        putExtra(getString(R.string.movieDetailsArg), movieViewData)
    }
}