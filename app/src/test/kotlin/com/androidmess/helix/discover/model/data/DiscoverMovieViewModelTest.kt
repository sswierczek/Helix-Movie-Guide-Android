package com.androidmess.helix.discover.model.data

import com.androidmess.helix.BaseTest
import com.androidmess.helix.common.model.data.Movie
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.amshove.kluent.shouldEqual
import org.junit.Test

class DiscoverMovieViewModelTest : BaseTest() {

    val movie = mock<Movie> {
        on { id } doReturn 1234
        on { title } doReturn "test title"
        on { posterPath } doReturn "/testPath"
    }

    @Test
    fun `Should have the same id as model`() {
        val movieViewModel = DiscoverMovieViewModel.fromMovie(movie)

        movieViewModel.id shouldEqual movie.id
    }

    @Test
    fun `Should have the same title as model`() {
        val movieViewModel = DiscoverMovieViewModel.fromMovie(movie)

        movieViewModel.title shouldEqual movie.title
    }

    @Test
    fun `Should have full image path from model's poster path`() {
        val movieViewModel = DiscoverMovieViewModel.fromMovie(movie)

        movieViewModel.imagePath shouldEqual "https://image.tmdb.org/t/p/w500${movie.posterPath}"
    }
}