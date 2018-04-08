package com.androidmess.helix.discover.model.data

import com.androidmess.helix.common.model.data.Movie

/**
 * View model discover screen.
 *
 * Contains mapper from model->view model conversion.
 */
data class DiscoverMovieViewModel(val id: Int, val title: String, val imagePath: String) {

    companion object Mapper {
        fun fromMovie(movie: Movie): DiscoverMovieViewModel {
            val imagePath = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            return DiscoverMovieViewModel(movie.id, movie.title, imagePath)
        }
    }
}
