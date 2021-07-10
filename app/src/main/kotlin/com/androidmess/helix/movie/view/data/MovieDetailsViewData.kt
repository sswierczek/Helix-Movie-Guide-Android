package com.androidmess.helix.movie.view.data

import android.os.Parcelable
import com.androidmess.helix.movie.model.data.MovieDetailsData
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailsViewData(
    val id: Int,
    val title: String,
    val imagePath: String,
    val vote: String
) : Parcelable

fun MovieDetailsData.viewData(): MovieDetailsViewData =
    MovieDetailsViewData(
        id,
        title,
        "https://image.tmdb.org/t/p/w500$posterPath",
        "$voteAverage"
    )