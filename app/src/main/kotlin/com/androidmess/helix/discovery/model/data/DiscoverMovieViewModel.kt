package com.androidmess.helix.discovery.model.data

import com.androidmess.helix.common.model.data.Movie

data class DiscoverMovieViewModel(val id: Int?, val title: String?, val imagePath: String) {
    companion object Mapper {
        fun fromMovie(movie: Movie): DiscoverMovieViewModel {
            var imagePath = "http://placehold.it/500?text=${movie.title}"
            movie.posterPath?.let {
                imagePath = "https://image.tmdb.org/t/p/w500$it"
            }
            return DiscoverMovieViewModel(movie.id, movie.title, imagePath)
        }
    }
}