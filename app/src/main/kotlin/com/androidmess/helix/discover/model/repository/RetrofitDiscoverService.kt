package com.androidmess.helix.discover.model.repository

import com.androidmess.helix.common.model.data.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitDiscoverService {

    @GET("discover/movie")
    fun discoverMovies(@Query("api_key") apiKey: String,
                       @Query("page") page: Int): Observable<MovieResult>
}