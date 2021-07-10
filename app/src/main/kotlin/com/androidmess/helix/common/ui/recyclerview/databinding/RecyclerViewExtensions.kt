package com.androidmess.helix.common.ui.recyclerview.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapterData", "modelData")
fun <T> RecyclerView.bindDataToRecyclerView(adapterData: MutableList<T>, modelData: List<T>?) {
    modelData?.let { data ->
        adapterData.addAll(data)
        adapter?.notifyItemRangeInserted(adapterData.size - 1, modelData.size)
    }
}
