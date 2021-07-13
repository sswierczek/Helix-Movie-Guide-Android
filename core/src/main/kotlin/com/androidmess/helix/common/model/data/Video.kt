package com.androidmess.helix.common.model.data

import com.google.gson.annotations.SerializedName

/**
 * Movie video model. More information here https://developers.themoviedb.org/3/movies/get-movie-videos
 */
data class Video(
    @SerializedName("key")
    val videoKey: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("type")
    val type: String
) {
    fun isTrailer() = type == "Trailer"
    fun isFromYouTube() = site == "YouTube"
}