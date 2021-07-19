package com.androidmess.helix.discover.model.repository

import com.androidmess.helix.common.network.NetworkConfig
import com.androidmess.helix.core.data.models.Movie
import com.androidmess.helix.core.data.models.Response
import com.androidmess.helix.core.data.models.map
import com.androidmess.helix.core.data.models.unknownError
import com.androidmess.helix.core.data.repository.Repository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

// FIXME Implement cache
class RetrofitDiscoverRepository(
    retrofit: Retrofit,
    private val networkConfig: NetworkConfig
) : Repository.Discover {

    private val service: RetrofitDiscoverService =
        retrofit.create(RetrofitDiscoverService::class.java)

    override suspend fun discover(page: Int): Response<List<Movie>> =
        withContext(Dispatchers.IO) {
            when (val response =
                service.discoverMovies(page = page, apiKey = networkConfig.apiKey)) {
                is NetworkResponse.Success -> {
                    Response.Success(response.body.results.map { it.map() })
                }
                is NetworkResponse.Error -> {
                    Response.Error(response.error.message, response.error)
                }
                else -> unknownError()
            }
        }
}
