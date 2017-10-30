package com.androidmess.helix.common.ui.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.androidmess.helix.common.rx.SchedulersInjector
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RecyclerViewOnScrolledToBottomDetector(private val schedulersInjector: SchedulersInjector,
                                             private val layoutManager: LinearLayoutManager)
    : RecyclerView.OnScrollListener() {

    private companion object {
        const val DEBOUNCE_TIME_MS = 200L
    }

    private val onScrolledToBottom: PublishSubject<Any> = PublishSubject.create()

    fun scrollEvents(onScrollEvents: Observable<RecyclerViewScrollEvent>): RecyclerViewOnScrolledToBottomDetector {
        onScrollEvents
                .debounce(DEBOUNCE_TIME_MS, TimeUnit.MILLISECONDS, schedulersInjector.computation())
                .subscribe {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        onScrolledToBottom.onNext(Any())
                    }
                }
        return this
    }

    fun observe(): Observable<Any> {
        return onScrolledToBottom
                .observeOn(schedulersInjector.ui())
    }
}