package com.androidmess.helix.core.data.models

import android.os.Parcelable
import com.androidmess.helix.core.data.api.ApiMovieDetails
import com.androidmess.helix.core.data.api.ApiMovieVideo
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails(
    val id: Int,
    val title: String,
    val imagePath: String,
    val vote: String,
    val youTubeVideosIds: List<String> = emptyList()
) : Parcelable

fun ApiMovieDetails.map(videos: List<ApiMovieVideo>): MovieDetails =
    MovieDetails(
        id,
        title,
        "https://image.tmdb.org/t/p/w500$posterPath",
        "$voteAverage",
        videos.filter { it.isFromYouTube() && it.isTrailer() }.map { it.videoKey }
    )