package com.androidmess.helix.movie.view.data

import android.os.Parcelable
import com.androidmess.helix.common.model.data.Movie
import kotlinx.parcelize.Parcelize

/**
 * View data of movie item.
 *
 * Contains mapper from model->view data conversion.
 */
@Parcelize
data class MovieViewData(val id: Int, val title: String, val imagePath: String) : Parcelable {

    companion object Mapper {
        fun fromMovie(movie: Movie): MovieViewData {
            val imagePath = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            return MovieViewData(movie.id, movie.title, imagePath)
        }
    }
}
