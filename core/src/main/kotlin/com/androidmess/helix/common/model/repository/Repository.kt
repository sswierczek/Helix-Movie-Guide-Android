package com.androidmess.helix.common.model.repository

import com.androidmess.helix.common.model.data.MovieResult
import io.reactivex.Observable

/**
 * Groups data repositories.
 *
 * Categorized by API categories https://developers.themoviedb.org/3/
 */
interface Repository {

    interface Discover {
        fun discoverMovies(page: Int): Observable<MovieResult>
    }
}