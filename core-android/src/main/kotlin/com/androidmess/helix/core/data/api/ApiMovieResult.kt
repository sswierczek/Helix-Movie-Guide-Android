package com.androidmess.helix.core.data.api

import com.google.gson.annotations.SerializedName

/**
 * Movie response model. More information here https://developers.themoviedb.org/3/companies/get-movies
 */
data class ApiMovieResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ApiMovie>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
