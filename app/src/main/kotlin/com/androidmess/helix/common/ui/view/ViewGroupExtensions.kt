package com.androidmess.helix.common.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
