package com.androidmess.helix.movie.view

import android.os.Bundle
import androidx.navigation.navArgs
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.databinding.ActivityMovieDetailsBinding
import com.androidmess.helix.movie.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : CompositeAppCompatActivity() {

    private val detailsViewModel: MovieDetailsViewModel by viewModel()
    private val args: MovieDetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDataBinding()
        super.onCreate(savedInstanceState)
        detailsViewModel.viewReady(movie = args.movieDetails)
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
}
