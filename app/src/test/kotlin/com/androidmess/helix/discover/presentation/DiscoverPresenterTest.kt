package com.androidmess.helix.discover.presentation

import com.androidmess.helix.BaseTest
import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import com.androidmess.helix.discover.view.DiscoverView
import com.nhaarman.mockito_kotlin.*
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

@Suppress("IllegalIdentifier")
class DiscoverPresenterTest : BaseTest() {

    @Mock
    lateinit var getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase

    @Mock
    lateinit var view: DiscoverView

    @Mock
    lateinit var data: MovieResult

    @InjectMocks
    lateinit var presenter: DiscoverPresenter

    val dataObservable: PublishSubject<MovieResult> = PublishSubject.create<MovieResult>()

    override fun setUp() {
        super.setUp()
        whenever(getDiscoverMoviesUseCase.execute(any())).thenReturn(dataObservable)
    }

    @Test
    fun `Should show loading when starting to fetch data`() {
        presenter.connect(view)

        verify(view).showLoading(true)
    }

    @Test
    fun `Should hide loading when data arrived`() {
        presenter.connect(view)

        dataObservable.onComplete()

        verify(view).showLoading(false)
    }

    @Test
    fun `Should show error when data loading error occurred`() {
        val error = Throwable("test error")
        presenter.connect(view)

        dataObservable.onError(error)

        verify(view).showError(error)
    }

    @Test
    fun `Should show data in view when finished fetching`() {
        presenter.connect(view)

        dataObservable.onNext(data)
        dataObservable.onComplete()

        verify(view).showMovies(any())
    }

    @Test
    fun `Should does nothing when view is visible`() {
        presenter.visible()

        verifyZeroInteractions(view)
    }

    @Test
    fun `Should does nothing when view is invisible`() {
        presenter.invisible()

        verifyZeroInteractions(view)
    }

    @Test
    fun `Should send nothing to view when disconnected`() {
        presenter.connect(view)
        presenter.disconnect()

        dataObservable.onNext(data)
        dataObservable.onComplete()

        verify(view, never()).showMovies(any())
    }

    @Test
    fun `Should load next page when scrolled to bottom`() {
        val nextPage = 2
        presenter.connect(view)
        dataObservable.onComplete()

        presenter.scrolledToBottom()

        verify(getDiscoverMoviesUseCase).execute(nextPage)
    }
}