package com.androidmess.helix.common.app

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

fun Context.getScreenWidth(): Int {
    val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}
