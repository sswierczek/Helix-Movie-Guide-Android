package com.androidmess.helix.common.ui.recyclerview.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import io.reactivex.Observable

@BindingAdapter("adapterData", "modelData")
fun <T> RecyclerView.bindDataToRecyclerView(adapterData: MutableList<T>, modelData: Observable<T>) {
    modelData.subscribe {
        adapterData.add(it)
        adapter.notifyItemInserted(adapterData.size - 1)
    }
}