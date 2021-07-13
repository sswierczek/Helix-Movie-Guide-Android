package com.androidmess.helix.movie.view.data

import android.os.Parcelable
import com.androidmess.helix.movie.model.data.MovieDetailsCombined
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailsViewData(
    val id: Int,
    val title: String,
    val imagePath: String,
    val vote: String,
    val youTubeVideosIds: List<String> = emptyList()
) : Parcelable

fun MovieDetailsCombined.viewData(): MovieDetailsViewData =
    MovieDetailsViewData(
        data.id,
        data.title,
        "https://image.tmdb.org/t/p/w500${data.posterPath}",
        "${data.voteAverage}",
        youTubeVideos.map { it.videoKey }
    )