package com.androidmess.helix.movie.model.repository

import com.androidmess.helix.core.data.api.ApiErrorResponse
import com.androidmess.helix.core.data.api.ApiMovieDetails
import com.androidmess.helix.core.data.api.ApiMovieVideosResult
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitMovieDetailsService {

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): NetworkResponse<ApiMovieDetails, ApiErrorResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun fetchMovieVideos(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): NetworkResponse<ApiMovieVideosResult, ApiErrorResponse>
}