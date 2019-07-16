package com.androidmess.helix.common.ui.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(path: String) {
    Glide.with(context).load(path).into(this)
}
