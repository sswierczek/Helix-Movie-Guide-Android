package com.androidmess.helix.common.ui.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class RecyclerViewOnScrolledToBottomDetector(private val layoutManager: LinearLayoutManager)
    : RecyclerView.OnScrollListener() {

    lateinit var onScrolledToBottom: () -> Unit

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
            onScrolledToBottom()
        }
    }
}