package com.androidmess.helix.movie.model.repository

import com.androidmess.helix.common.model.data.MovieVideosResult
import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.common.network.NetworkConfig
import com.androidmess.helix.movie.model.data.MovieDetailsCombined
import com.androidmess.helix.movie.model.data.MovieDetailsData
import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith
import retrofit2.Retrofit

class RetrofitMovieDetailsRepository(
    retrofit: Retrofit,
    private val networkConfig: NetworkConfig
) : Repository.MovieDetails {

    private val service: RetrofitMovieDetailsService =
        retrofit.create(RetrofitMovieDetailsService::class.java)

    override fun fetchMovieDetails(id: Int): Observable<MovieDetailsData> =
        service.fetchMovieDetails(id, networkConfig.apiKey)

    override fun fetchMovieVideos(id: Int): Observable<MovieVideosResult> =
        service.fetchMovieVideos(id, networkConfig.apiKey)

    override fun fetchMovieDetailsCombined(id: Int): Observable<MovieDetailsCombined> =
        fetchMovieDetails(id)
            .zipWith(fetchMovieVideos(id))
            .map { pair ->
                MovieDetailsCombined(
                    data = pair.first,
                    youTubeVideos = pair.second.results.filter { it.isFromYouTube() && it.isTrailer() }
                )
            }
}