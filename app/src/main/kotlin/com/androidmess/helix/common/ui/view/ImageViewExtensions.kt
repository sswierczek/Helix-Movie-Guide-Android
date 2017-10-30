package com.androidmess.helix.common.ui.view

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(path: String) {
    Picasso.with(context).load(path).into(this)
}