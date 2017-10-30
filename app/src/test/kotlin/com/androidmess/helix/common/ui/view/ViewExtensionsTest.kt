package com.androidmess.helix.common.ui.view

import android.view.View
import com.androidmess.helix.BaseTest
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.mockito.Mock

@Suppress("IllegalIdentifier")
class ViewExtensionsTest : BaseTest() {

    @Mock
    lateinit var view: View

    @Test
    fun `When show view visibility is VISIBLE`() {
        view.show(true)

        verify(view).visibility = View.VISIBLE
    }

    @Test
    fun `When hide view visibility is GONE`() {
        view.show(false)

        verify(view).visibility = View.GONE
    }
}