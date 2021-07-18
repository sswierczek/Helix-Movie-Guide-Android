package com.androidmess.helix.data.api

data class ApiMovieDetailsCombined(
    val data: ApiMovieDetails,
    val youTubeVideos: List<ApiMovieVideo>
)