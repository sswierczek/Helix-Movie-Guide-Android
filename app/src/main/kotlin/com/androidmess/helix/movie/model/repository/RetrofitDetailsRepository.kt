package com.androidmess.helix.movie.model.repository

import com.androidmess.helix.common.network.NetworkConfig
import com.androidmess.helix.core.data.models.MovieDetails
import com.androidmess.helix.core.data.models.Response
import com.androidmess.helix.core.data.models.map
import com.androidmess.helix.core.data.models.unknownError
import com.androidmess.helix.core.data.repository.Repository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class RetrofitDetailsRepository(
    retrofit: Retrofit,
    private val networkConfig: NetworkConfig
) : Repository.Details {

    private val service: RetrofitMovieDetailsService =
        retrofit.create(RetrofitMovieDetailsService::class.java)

    override suspend fun fetch(id: Int): Response<MovieDetails> =
        withContext(Dispatchers.IO) {
            val response = service.fetchMovieDetails(id, networkConfig.apiKey)
            val responseVideos = service.fetchMovieVideos(id, networkConfig.apiKey)
            when {
                response is NetworkResponse.Success && responseVideos is NetworkResponse.Success -> {
                    Response.Success(
                        response.body.map(videos = responseVideos.body.results)
                    )
                }
                response is NetworkResponse.Success -> {
                    Response.Success(
                        response.body.map(videos = emptyList())
                    )
                }
                response is NetworkResponse.Error -> {
                    Response.Error(
                        response.error.message,
                        response.error
                    )
                }
                else -> unknownError()
            }
        }
}