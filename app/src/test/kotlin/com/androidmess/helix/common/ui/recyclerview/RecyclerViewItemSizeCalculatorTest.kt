package com.androidmess.helix.common.ui.recyclerview

import com.androidmess.helix.BaseTest
import org.amshove.kluent.shouldEqual
import org.junit.Test

@Suppress("IllegalIdentifier", "MemberVisibilityCanPrivate")
class RecyclerViewItemSizeCalculatorTest : BaseTest() {

    companion object {
        const val SCREEN_WIDTH = 320
    }

    val calculator = RecyclerViewItemSizeCalculator(SCREEN_WIDTH)

    @Test
    fun `Default span count is 1`() {
        calculator.spanCount shouldEqual 1
    }

    @Test
    fun `Should returns the same item width as screen when span count is 1`() {
        calculator.spanCount = 1

        calculator.itemWidth shouldEqual SCREEN_WIDTH
    }

    @Test
    fun `Should divide screen width based on span count`() {
        calculator.spanCount = 2

        calculator.itemWidth shouldEqual SCREEN_WIDTH / calculator.spanCount
    }
}