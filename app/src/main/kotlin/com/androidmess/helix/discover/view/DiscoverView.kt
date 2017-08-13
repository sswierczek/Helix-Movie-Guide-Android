package com.androidmess.helix.discover.view

import com.androidmess.helix.common.presentation.Mvp
import com.androidmess.helix.discover.model.data.DiscoverMovieViewModel

interface DiscoverView : Mvp.View {

    fun showLoading(show: Boolean)

    fun showMovies(movies: List<DiscoverMovieViewModel>)

    fun showError(error: Throwable?)
}
