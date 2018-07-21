package com.androidmess.helix.discover.presentation

import com.androidmess.helix.BaseTest
import com.androidmess.helix.common.model.data.Movie
import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.discover.view.DiscoverViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.subjects.PublishSubject
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class DiscoverViewModelTest : BaseTest() {

    val getDiscoverMoviesUseCase = mock<GetDiscoverMoviesUseCase>()
    val dataItem = mock<Movie>()
    val dataList = listOf(dataItem)
    val data = mock<MovieResult>()
    val dataObservable = PublishSubject.create<MovieResult>()

    val viewModel = DiscoverViewModel(testSchedulers, getDiscoverMoviesUseCase)

    @Before
    fun setUp() {
        whenever(getDiscoverMoviesUseCase.execute(any())).thenReturn(dataObservable)
        whenever(data.results).thenReturn(dataList)
    }

    @Test
    fun `Should show loading when starting to fetch data`() {
        viewModel.viewReady()

        viewModel.progress.get() shouldEqual true
    }

    @Test
    fun `Should hide loading when data arrived`() {
        viewModel.viewReady()

        dataObservable.onComplete()

        viewModel.progress.get() shouldEqual false
    }

    @Test
    fun `Should show error when data loading error occurred`() {
        val error = Throwable("test error")
        viewModel.viewReady()

        dataObservable.onError(error)

        viewModel.error.get() shouldEqual true
    }

    // FIXME Introduce state in view model and test scroll events

    @Test
    fun `Should not load next page when previous fetch is still in progress`() {
        val firstPage = 1
        viewModel.viewReady()

        viewModel.scroll.notifyChange()

        verify(getDiscoverMoviesUseCase).execute(firstPage)
    }
}
