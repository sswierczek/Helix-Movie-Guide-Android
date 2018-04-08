package com.androidmess.helix.common.di.activity

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.koin.KoinContext
import org.koin.standalone.StandAloneContext

class DiActivityPluginTest {

    val contextName = "SomeContext"
    val diContext = mock<KoinContext>()
    val plugin = DiActivityPlugin(mock(), contextName)

    @Before
    fun setup() {
        StandAloneContext.koinContext = diContext
    }

    @Test
    fun `When activity is stopping and not finishing do nothing`() {
        plugin.onStop(false)

        verifyZeroInteractions(diContext)
    }

    @Test
    fun `When activity is stopping and finishing do release context`() {
        plugin.onStop(true)

        verify(diContext).releaseContext(contextName)
    }

    @Test
    fun `When activity is destroying release context`() {
        plugin.onDestroy(true)

        verify(diContext).releaseContext(contextName)
    }
}
