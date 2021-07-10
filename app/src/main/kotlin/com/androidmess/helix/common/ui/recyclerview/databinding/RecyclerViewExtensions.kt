package com.androidmess.helix.common.ui.recyclerview.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("modelData")
fun <T> RecyclerView.bindDataToRecyclerView(modelData: List<T>?) {
    modelData?.let {
        (adapter as? ListAdapter<T, RecyclerView.ViewHolder>)?.submitList(modelData)
    }
}
