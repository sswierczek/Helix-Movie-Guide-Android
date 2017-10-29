package com.androidmess.helix.discover.model.data

import com.androidmess.helix.BaseTest
import com.androidmess.helix.common.model.data.Movie
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.mockito.Mock

@Suppress("IllegalIdentifier")
class DiscoverMovieViewModelTest : BaseTest() {

    @Mock
    lateinit var movie: Movie

    override fun setUp() {
        super.setUp()
        whenever(movie.id).thenReturn(1234)
        whenever(movie.title).thenReturn("test title")
        whenever(movie.posterPath).thenReturn("/testPath")
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