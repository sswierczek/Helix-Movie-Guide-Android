package com.androidmess.helix.movie.view.data

import com.androidmess.helix.BaseTest
import com.androidmess.helix.common.model.data.Video
import com.androidmess.helix.movie.model.data.MovieDetailsCombined
import com.androidmess.helix.movie.model.data.MovieDetailsData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.shouldEqual
import org.junit.Test

class MovieDetailsViewDataTest : BaseTest() {

    val movieDetailsData = mock<MovieDetailsData> {
        on { id } doReturn 1234
        on { title } doReturn "test title"
        on { posterPath } doReturn "/testPath"
        on { voteAverage } doReturn 8.8
    }
    val videos = listOf(Video("TestVideo1234", "YouTube", "Trailer"))
    val movieDetailsCombined = MovieDetailsCombined(
        movieDetailsData,
        videos
    )

    @Test
    fun `Should have the same id as model`() {
        val movieViewModel = movieDetailsCombined.viewData()

        movieViewModel.id shouldEqual movieDetailsData.id
    }

    @Test
    fun `Should have the same title as model`() {
        val movieViewModel = movieDetailsCombined.viewData()

        movieViewModel.title shouldEqual movieDetailsData.title
    }

    @Test
    fun `Should have full image path from model's poster path`() {
        val movieViewModel = movieDetailsCombined.viewData()

        movieViewModel.imagePath shouldEqual "https://image.tmdb.org/t/p/w500${movieDetailsData.posterPath}"
    }

    @Test
    fun `Should have vote average as text`() {
        val movieViewModel = movieDetailsCombined.viewData()

        movieViewModel.vote shouldEqual "${movieDetailsData.voteAverage}"
    }

    @Test
    fun `Should have videos`() {
        val movieViewModel = movieDetailsCombined.viewData()

        movieViewModel.youTubeVideosIds shouldEqual listOf("TestVideo1234")
    }
}
