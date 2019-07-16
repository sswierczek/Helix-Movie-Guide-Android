package com.androidmess.helix.common.ui.view

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

@BindingAdapter("show")
fun View.show(isVisible: Boolean) {
    if (isVisible) this.visibility = VISIBLE else this.visibility = GONE
}
