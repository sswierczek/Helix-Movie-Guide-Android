package com.androidmess.helix.discovery.model.repository

import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.model.repository.Repository

import io.reactivex.Observable
import retrofit2.Retrofit

// FIXME Implement cache
class RetrofitDiscoverRepository(retrofit: Retrofit, val apiKey: String) : Repository.Discover {

    private val service: RetrofitDiscoverService = retrofit.create(RetrofitDiscoverService::class.java)

    override fun discoverMovies(page: Int): Observable<MovieResult> {
        return service.discoverMovies(page = page, apiKey = apiKey)
    }
}