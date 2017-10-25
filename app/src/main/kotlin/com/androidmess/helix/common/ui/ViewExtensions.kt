package com.androidmess.helix.common.ui;

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun View.show(isVisible: Boolean) {
    if (isVisible) this.visibility = VISIBLE else this.visibility = GONE
}

fun ImageView.load(path: String) {
    Picasso.with(context).load(path).into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean): View
        = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)