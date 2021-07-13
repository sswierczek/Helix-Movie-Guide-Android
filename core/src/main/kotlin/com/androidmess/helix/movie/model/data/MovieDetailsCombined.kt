package com.androidmess.helix.movie.model.data

import com.androidmess.helix.common.model.data.Video

data class MovieDetailsCombined(
    val data: MovieDetailsData,
    val youTubeVideos: List<Video>
)