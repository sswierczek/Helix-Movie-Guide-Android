package com.androidmess.helix.common.ui.recyclerview

import android.support.v7.widget.GridLayoutManager
import com.androidmess.helix.BaseTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

@Suppress("IllegalIdentifier")
class RecyclerViewOnScrolledToBottomDetectorTest : BaseTest() {

    @Mock
    lateinit var layoutManager: GridLayoutManager

    @InjectMocks
    lateinit var detector: RecyclerViewOnScrolledToBottomDetector

    @Test
    fun `tests `() {
        // FIXME Add tests for RecyclerViewOnScrolledToBottomDetector
    }
}