package com.androidmess.helix.common.ui.view

import android.databinding.BindingAdapter
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

@BindingAdapter("bind:show")
fun View.show(isVisible: Boolean) {
    if (isVisible) this.visibility = VISIBLE else this.visibility = GONE
}