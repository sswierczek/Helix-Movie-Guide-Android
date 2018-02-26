package com.androidmess.helix.movie.model.repository

import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.common.network.NetworkConfig
import com.androidmess.helix.movie.model.data.MovieDetailsData
import io.reactivex.Observable
import retrofit2.Retrofit

class RetrofitMovieDetailsRepository(
    retrofit: Retrofit,
    private val networkConfig: NetworkConfig
) : Repository.MovieDetails {

    private val service: RetrofitMovieDetailsService =
        retrofit.create(RetrofitMovieDetailsService::class.java)

    override fun fetchMovieDetails(id: Int): Observable<MovieDetailsData> {
        return service.fetchMovieDetails(id, networkConfig.apiKey)
    }
}