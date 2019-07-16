package com.androidmess.helix.movie.view.data

import com.androidmess.helix.BaseTest
import com.androidmess.helix.common.model.data.Movie
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.shouldEqual
import org.junit.Test

class MovieViewDataTest : BaseTest() {

    val movie = mock<Movie> {
        on { id } doReturn 1234
        on { title } doReturn "test title"
        on { posterPath } doReturn "/testPath"
    }

    @Test
    fun `Should have the same id as model`() {
        val movieViewModel = MovieViewData.fromMovie(movie)

        movieViewModel.id shouldEqual movie.id
    }

    @Test
    fun `Should have the same title as model`() {
        val movieViewModel = MovieViewData.fromMovie(movie)

        movieViewModel.title shouldEqual movie.title
    }

    @Test
    fun `Should have full image path from model's poster path`() {
        val movieViewModel = MovieViewData.fromMovie(movie)

        movieViewModel.imagePath shouldEqual "https://image.tmdb.org/t/p/w500${movie.posterPath}"
    }
}
