package com.androidmess.helix.common.ui.recyclerview

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Test
import reactivecircus.flowbinding.recyclerview.RecyclerViewScrollEvent
import kotlin.time.ExperimentalTime

@ExperimentalTime
class RecyclerViewOnScrolledToBottomDetectorTest {

    companion object {
        const val VISIBLE_ITEMS = 2
        const val ITEM_COUNT = 10
    }

    val sampleScrollEvent = mockk<RecyclerViewScrollEvent>()
    val layoutManager = mockk<androidx.recyclerview.widget.LinearLayoutManager> {
        every { itemCount } returns ITEM_COUNT
        every { childCount } returns VISIBLE_ITEMS
    }

    val detector = RecyclerViewOnScrolledToBottomDetector(layoutManager)

    @Test
    fun `When no scroll events does nothing`() = runBlockingTest {
        detector.onScrolledToBottom.test {
            expectNoEvents()
        }
    }

    @Test
    fun `When scrolled to bottom send on scrolled to bottom event`() = runBlockingTest {
        every { layoutManager.findFirstVisibleItemPosition() } returns ITEM_COUNT - VISIBLE_ITEMS

        detector.onScrolledToBottom.test {
            val testScope = TestCoroutineScope()
            detector.scrollEvents(onScrollEvents = flowOf(sampleScrollEvent), testScope)
            testScope.runCurrent()

            expectItem() shouldEqual Unit
        }
    }

    @Test
    fun `When scrolled but bottom is not reached does nothing`() = runBlockingTest {
        every { layoutManager.findFirstVisibleItemPosition() } returns VISIBLE_ITEMS

        detector.onScrolledToBottom.test {
            val testScope = TestCoroutineScope()
            detector.scrollEvents(onScrollEvents = flowOf(sampleScrollEvent), testScope)
            testScope.runCurrent()

            expectNoEvents()
        }
    }
}