package com.androidmess.helix.common.model.data

import com.google.gson.annotations.SerializedName

/**
 * Movie videos response model. More information here https://developers.themoviedb.org/3/movies/get-movie-videos
 */
data class MovieVideosResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Video>
)