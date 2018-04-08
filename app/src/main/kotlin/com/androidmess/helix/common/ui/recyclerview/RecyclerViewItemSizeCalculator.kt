package com.androidmess.helix.common.ui.recyclerview

class RecyclerViewItemSizeCalculator(private val screenWidth: Int) {

    val itemWidth: Int by lazy {
        screenWidth / spanCount
    }

    var spanCount: Int = 1
}
