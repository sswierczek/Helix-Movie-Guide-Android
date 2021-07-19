package com.androidmess.helix.common.navigation

import android.view.View
import androidx.navigation.findNavController
import com.androidmess.helix.core.data.models.Movie
import com.androidmess.helix.discover.view.DiscoverFragmentDirections

class Navigator {

    fun onMovieClick(view: View, movie: Movie) {
        view.findNavController().navigate(
            DiscoverFragmentDirections.actionDiscoverFragmentToMovieDetailsActivity(movie)
        )
    }
}
