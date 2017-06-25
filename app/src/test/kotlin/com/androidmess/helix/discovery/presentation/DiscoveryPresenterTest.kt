package com.androidmess.helix.discovery.presentation

import com.androidmess.helix.BaseTest
import com.androidmess.helix.discovery.usecase.GetDiscoveryMoviesUseCase
import com.androidmess.helix.discovery.view.DiscoverView
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

@Suppress("IllegalIdentifier")
class DiscoveryPresenterTest : BaseTest() {

    @Mock
    lateinit var getDiscoveryMoviesUseCase: GetDiscoveryMoviesUseCase

    @Mock
    lateinit var view: DiscoverView

    @InjectMocks
    lateinit var presenter: DiscoveryPresenter

    @Test
    fun `test test`() {
        // TODO Add DiscoveryPresenter tests
    }
}