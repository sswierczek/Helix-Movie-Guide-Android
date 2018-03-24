package com.androidmess.helix.common.ui.recyclerview

import android.support.v7.widget.GridLayoutManager
import com.androidmess.helix.BaseTest
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier", "MemberVisibilityCanPrivate")
class RecyclerViewOnScrolledToBottomDetectorTest : BaseTest() {

    companion object {
        const val VISIBLE_ITEMS = 2
        const val ITEM_COUNT = 10
    }

    val scrollEvents: PublishSubject<RecyclerViewScrollEvent> = PublishSubject.create<RecyclerViewScrollEvent>()

    val sampleScrollEvent: RecyclerViewScrollEvent = mock()

    val layoutManager: GridLayoutManager = mock()

    val detector: RecyclerViewOnScrolledToBottomDetector = RecyclerViewOnScrolledToBottomDetector(testSchedulers, layoutManager)

    @Before
    fun setUp() {
        whenever(layoutManager.itemCount).thenReturn(ITEM_COUNT)
        whenever(layoutManager.childCount).thenReturn(VISIBLE_ITEMS)
        detector.scrollEvents(scrollEvents)
    }

    @Test
    fun `When no scroll events does nothing`() {
        detector.observe().test().assertEmpty()
    }

    @Test
    fun `When scrolled to bottom send on scrolled to bottom event`() {
        whenever(layoutManager.findFirstVisibleItemPosition()).thenReturn(ITEM_COUNT - VISIBLE_ITEMS)

        val testSubscriber = detector.observe().test()
        scrollEvents.onNext(sampleScrollEvent)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }

    @Test
    fun `When scrolled but bottom is not reached does nothing`() {
        whenever(layoutManager.findFirstVisibleItemPosition()).thenReturn(VISIBLE_ITEMS)

        val testSubscriber = detector.observe().test()
        scrollEvents.onNext(sampleScrollEvent)

        testSubscriber.assertEmpty()
    }
}