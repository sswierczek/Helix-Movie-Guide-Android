package com.androidmess.helix.movie.view.data

import com.androidmess.helix.core.data.api.ApiMovieDetails
import com.androidmess.helix.core.data.api.ApiMovieVideo
import com.androidmess.helix.core.data.models.map
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldEqual
import org.junit.Test

class DetailsTest {

    val movieDetailsData = mockk<ApiMovieDetails> {
        every { id } returns 1234
        every { title } returns "test title"
        every { posterPath } returns "/testPath"
        every { voteAverage } returns 8.8
    }
    val videos = listOf(ApiMovieVideo("TestVideo1234", "YouTube", "Trailer"))

    @Test
    fun `Should have the same id as model`() {
        val movieViewModel = movieDetailsData.map(videos)

        movieViewModel.id shouldEqual movieDetailsData.id
    }

    @Test
    fun `Should have the same title as model`() {
        val movieViewModel = movieDetailsData.map(videos)

        movieViewModel.title shouldEqual movieDetailsData.title
    }

    @Test
    fun `Should have full image path from model's poster path`() {
        val movieViewModel = movieDetailsData.map(videos)

        movieViewModel.imagePath shouldEqual "https://image.tmdb.org/t/p/w500${movieDetailsData.posterPath}"
    }

    @Test
    fun `Should have vote average as text`() {
        val movieViewModel = movieDetailsData.map(videos)

        movieViewModel.vote shouldEqual "${movieDetailsData.voteAverage}"
    }

    @Test
    fun `Should have videos`() {
        val movieViewModel = movieDetailsData.map(videos)

        movieViewModel.youTubeVideosIds shouldEqual listOf("TestVideo1234")
    }
}
