package com.androidmess.helix.common.ui.view

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(path: String) {
    Picasso.get().load(path).into(this)
}