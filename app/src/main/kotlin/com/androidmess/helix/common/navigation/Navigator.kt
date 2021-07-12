package com.androidmess.helix.common.navigation

import android.view.View
import androidx.navigation.findNavController
import com.androidmess.helix.discover.view.DiscoverFragmentDirections
import com.androidmess.helix.movie.view.data.MovieViewData

class Navigator {

    fun onMovieClick(view: View, movie: MovieViewData) {
        view.findNavController().navigate(
            DiscoverFragmentDirections.actionDiscoverFragmentToMovieDetailsActivity(movie)
        )
    }
}
