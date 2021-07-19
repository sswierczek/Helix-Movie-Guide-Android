package com.androidmess.helix.movie.view.data

import com.androidmess.helix.core.data.api.ApiMovie
import com.androidmess.helix.core.data.models.map
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldEqual
import org.junit.Test

class MovieViewDataTest {

    val apiMovie = mockk<ApiMovie> {
        every { id } returns 1234
        every { title } returns "test title"
        every { posterPath } returns "/testPath"
    }

    @Test
    fun `Should have the same id as model`() {
        val movieViewModel = apiMovie.map()

        movieViewModel.id shouldEqual apiMovie.id
    }

    @Test
    fun `Should have the same title as model`() {
        val movieViewModel = apiMovie.map()

        movieViewModel.title shouldEqual apiMovie.title
    }

    @Test
    fun `Should have full image path from model's poster path`() {
        val movieViewModel = apiMovie.map()

        movieViewModel.imagePath shouldEqual "https://image.tmdb.org/t/p/w500${apiMovie.posterPath}"
    }
}
