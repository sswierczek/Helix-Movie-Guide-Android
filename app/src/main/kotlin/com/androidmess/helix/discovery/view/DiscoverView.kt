package com.androidmess.helix.discovery.view

import com.androidmess.helix.common.presentation.Mvp
import com.androidmess.helix.discovery.model.data.DiscoverMovieViewModel

interface DiscoverView : Mvp.View {

    fun showLoading(show: Boolean)

    fun showMovies(movies: List<DiscoverMovieViewModel>)

    fun showError(error: Throwable?)
}
