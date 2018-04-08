package com.androidmess.helix.common.activity.plugin

import android.os.Bundle

interface CompositeActivityPlugin {

    fun onCreate(savedInstanceState: Bundle?) {}

    fun onResume() {}

    fun onPause() {}

    fun onStart() {}

    fun onStop(isFinishing: Boolean) {}

    fun onDestroy(isFinishing: Boolean) {}
}
