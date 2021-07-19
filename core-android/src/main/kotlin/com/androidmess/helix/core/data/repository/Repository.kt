package com.androidmess.helix.core.data.repository

import com.androidmess.helix.core.data.models.Movie
import com.androidmess.helix.core.data.models.MovieDetails
import com.androidmess.helix.core.data.models.Response

/**
 * Groups data repositories.
 *
 * Categorized by API categories https://developers.themoviedb.org/3/
 */
interface Repository {
    interface Discover {
        suspend fun discover(page: Int): Response<List<Movie>>
    }

    interface Details {
        suspend fun fetch(id: Int): Response<MovieDetails>
    }
}
