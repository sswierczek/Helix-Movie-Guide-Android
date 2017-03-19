package com.androidmess.helix.discovery.view

import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.presentation.Mvp

interface DiscoverView : Mvp.View {

    fun showLoading(show: Boolean)

    fun showMovies(movies: MovieResult) // FIXME Add ViewModel mapping

    fun showError(error: Throwable?)
}
