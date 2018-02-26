package com.androidmess.helix.movie.model.repository

import com.androidmess.helix.movie.model.data.MovieDetailsData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitMovieDetailsService {

    @GET("movie/{movie_id}")
    fun fetchMovieDetails(@Path("movie_id") id: Int,
                          @Query("api_key") apiKey: String): Observable<MovieDetailsData>
}