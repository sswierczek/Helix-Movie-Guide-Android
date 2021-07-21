package com.androidmess.helix.common.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.recyclerview.RecyclerViewScrollEvent

class RecyclerViewOnScrolledToBottomDetector(
    private val layoutManager: androidx.recyclerview.widget.LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private companion object {
        const val DEBOUNCE_TIME_MS = 200L
    }

    private val _onScrolledToBottom = MutableSharedFlow<Unit>()
    val onScrolledToBottom = _onScrolledToBottom.asSharedFlow()

    fun scrollEvents(
        onScrollEvents: Flow<RecyclerViewScrollEvent>,
        scope: CoroutineScope
    ): RecyclerViewOnScrolledToBottomDetector {
        onScrollEvents
            .debounce(DEBOUNCE_TIME_MS)
            .onEach {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    _onScrolledToBottom.emit(Unit)
                }
            }
            .launchIn(scope)
        return this
    }
}
