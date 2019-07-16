package com.androidmess.helix.common.navigation

import android.app.Activity
import com.androidmess.helix.movie.view.data.MovieViewData
import com.androidmess.helix.movie.view.movieDetailsIntent

// TODO Use navigation
class Navigator {
    var activity: Activity? = null

    fun attach(activity: Activity) {
        this.activity = activity
    }

    fun detach() {
        this.activity = null
    }

    fun onMovieClick(movie: MovieViewData) {
        activity?.run {
            startActivity(movieDetailsIntent(movie))
        }
    }
}