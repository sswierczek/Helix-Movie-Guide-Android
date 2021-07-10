package com.androidmess.helix.movie.view.data

import android.os.Parcelable
import com.androidmess.helix.common.model.data.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieViewData(
    val id: Int,
    val title: String,
    val imagePath: String
) : Parcelable

fun Movie.viewData(): MovieViewData =
    MovieViewData(
        id,
        title,
        "https://image.tmdb.org/t/p/w500$posterPath"
    )