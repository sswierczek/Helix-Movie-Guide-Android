package com.androidmess.helix.discover.presentation

import com.androidmess.helix.BaseTest
import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier", "MemberVisibilityCanPrivate")
class DiscoverPresenterTest : BaseTest() {

    val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase = mock()

    val view: DiscoverView = mock()

    val data: MovieResult = mock()

    val presenter: DiscoverPresenter = DiscoverPresenter(testSchedulers, getDiscoverMoviesUseCase)

    val dataObservable: PublishSubject<MovieResult> = PublishSubject.create<MovieResult>()

    @Before
    fun setUp() {
        whenever(getDiscoverMoviesUseCase.execute(any())).thenReturn(dataObservable)
        presenter.connect(view)
    }

    @Test
    fun `Should show loading when starting to fetch data`() {
        presenter.visible(view)

        verify(view).showLoading(true)
    }

    @Test
    fun `Should hide loading when data arrived`() {
        presenter.visible(view)

        dataObservable.onComplete()

        verify(view).showLoading(false)
    }

    @Test
    fun `Should show error when data loading error occurred`() {
        val error = Throwable("test error")
        presenter.visible(view)

        dataObservable.onError(error)

        verify(view).showError(error)
    }

    @Test
    fun `Should show data in view when finished fetching`() {
        presenter.visible(view)

        dataObservable.onNext(data)
        dataObservable.onComplete()

        verify(view).showMovies(any())
    }

    @Test
    fun `Should does nothing when view is invisible`() {
        presenter.invisible()

        verifyZeroInteractions(view)
    }

    @Test
    fun `Should send nothing to view when disconnected`() {
        presenter.disconnect()

        dataObservable.onNext(data)
        dataObservable.onComplete()

        verify(view, never()).showMovies(any())
    }

    @Test
    fun `Should load next page when scrolled to bottom`() {
        val nextPage = 2
        presenter.visible(view)
        dataObservable.onComplete()

        presenter.scrolledToBottom()

        verify(getDiscoverMoviesUseCase).execute(nextPage)
    }

    @Test
    fun `Should not load next page when previous fetch is still in progress`() {
        val firstPage = 1
        presenter.visible(view)

        presenter.scrolledToBottom()

        verify(getDiscoverMoviesUseCase).execute(firstPage)
    }
}