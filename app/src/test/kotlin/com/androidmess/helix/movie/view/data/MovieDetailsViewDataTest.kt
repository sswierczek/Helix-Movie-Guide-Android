package com.androidmess.helix.movie.view.data

import com.androidmess.helix.BaseTest
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

    @Test
    fun `Should have the same id as model`() {
        val movieViewModel = movieDetailsData.viewData()

        movieViewModel.id shouldEqual movieDetailsData.id
    }

    @Test
    fun `Should have the same title as model`() {
        val movieViewModel = movieDetailsData.viewData()

        movieViewModel.title shouldEqual movieDetailsData.title
    }

    @Test
    fun `Should have full image path from model's poster path`() {
        val movieViewModel = movieDetailsData.viewData()

        movieViewModel.imagePath shouldEqual "https://image.tmdb.org/t/p/w500${movieDetailsData.posterPath}"
    }

    @Test
    fun `Should have vote average as text`() {
        val movieViewModel = movieDetailsData.viewData()

        movieViewModel.vote shouldEqual "${movieDetailsData.voteAverage}"
    }
}
