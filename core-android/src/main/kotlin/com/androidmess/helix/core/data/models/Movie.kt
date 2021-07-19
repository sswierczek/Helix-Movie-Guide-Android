package com.androidmess.helix.core.data.models

import android.os.Parcelable
import com.androidmess.helix.core.data.api.ApiMovie
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val imagePath: String
) : Parcelable

fun ApiMovie.map(): Movie =
    Movie(
        id,
        title,
        "https://image.tmdb.org/t/p/w500$posterPath"
    )