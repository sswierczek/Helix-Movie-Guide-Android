package com.androidmess.helix.discover.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androidmess.helix.CoroutinesTestRule
import com.androidmess.helix.core.data.models.Movie
import com.androidmess.helix.core.data.models.Response
import com.androidmess.helix.core.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.discover.view.DiscoverViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Rule
import org.junit.Test

class DiscoverViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    val getDiscoverMoviesUseCase = mockk<GetDiscoverMoviesUseCase>()
    val dataItem = mockk<Movie>()
    val dataList = listOf(dataItem)

    val viewModel = DiscoverViewModel(getDiscoverMoviesUseCase)

    @Test
    fun `Should show loading when starting to fetch data`() {
        viewModel.viewReady()

        viewModel.progress.value shouldEqual true
    }

    @Test
    fun `Should hide loading when data arrived`() {
        coEvery { getDiscoverMoviesUseCase.execute(any()) } returns Response.Success(dataList)

        viewModel.viewReady()

        viewModel.progress.value shouldEqual false
    }

    @Test
    fun `Should show error when data loading error occurred`() {
        coEvery { getDiscoverMoviesUseCase.execute(any()) } returns
                Response.Error("test error", Throwable("test error"))

        viewModel.viewReady()

        viewModel.error.value shouldEqual true
    }

    // FIXME Introduce state in view model and test scroll events

    @ExperimentalCoroutinesApi
    @Test
    fun `Should not load next page when previous fetch is still in progress`() =
        runBlockingTest {
            val firstPage = 1
            viewModel.viewReady()

            viewModel.onLoadNextData()

            coVerify { getDiscoverMoviesUseCase.execute(firstPage) }
        }
}
