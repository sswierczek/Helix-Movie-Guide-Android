package com.androidmess.helix.common.ui.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl")
fun ImageView.loadImage(path: String?) {
    path?.let {
        Glide.with(context)
            .load(it)
            .into(this)
    }
}

@BindingAdapter("backgroundImageUrl")
fun ImageView.loadBackground(path: String?) {
    path?.let {
        Glide.with(context)
            .load(it)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}
