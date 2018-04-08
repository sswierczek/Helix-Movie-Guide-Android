package com.androidmess.helix.common.ui.view

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(path: String) {
    Glide.with(context).load(path).into(this)
}
