package com.androidmess.helix.core.data.api

data class ApiMovieDetailsCombined(
    val data: ApiMovieDetails,
    val youTubeVideos: List<ApiMovieVideo>
)