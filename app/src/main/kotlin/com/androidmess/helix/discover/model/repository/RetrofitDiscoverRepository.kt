package com.androidmess.helix.discover.model.repository

import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.common.network.NetworkConfig

import io.reactivex.Observable
import retrofit2.Retrofit

// FIXME Implement cache
class RetrofitDiscoverRepository(
    retrofit: Retrofit,
    private val networkConfig: NetworkConfig
) : Repository.Discover {

    private val service: RetrofitDiscoverService =
        retrofit.create(RetrofitDiscoverService::class.java)

    override fun discoverMovies(page: Int): Observable<MovieResult> {
        return service.discoverMovies(page = page, apiKey = networkConfig.apiKey)
    }
}
