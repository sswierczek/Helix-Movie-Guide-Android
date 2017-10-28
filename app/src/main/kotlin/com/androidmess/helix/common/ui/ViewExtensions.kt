package com.androidmess.helix.common.ui

import android.content.Context
import android.graphics.Point
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
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

fun Context.getScreenWidth(): Int {
    val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}