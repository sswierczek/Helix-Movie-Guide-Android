package com.androidmess.helix.data.api

import com.google.gson.annotations.SerializedName

/**
 * Movie videos response model. More information here https://developers.themoviedb.org/3/movies/get-movie-videos
 */
data class ApiMovieVideosResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<ApiMovieVideo>
)