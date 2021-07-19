package com.androidmess.helix.discover.model.repository

import com.androidmess.helix.core.data.api.ApiErrorResponse
import com.androidmess.helix.core.data.api.ApiMovieResult
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitDiscoverService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): NetworkResponse<ApiMovieResult, ApiErrorResponse>
}
