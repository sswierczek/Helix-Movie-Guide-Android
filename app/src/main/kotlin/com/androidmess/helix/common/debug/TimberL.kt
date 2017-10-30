package com.androidmess.helix.common.debug

import com.androidmess.helix.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class TimberL : L {

    override fun init() {
        if (BuildConfig.DEBUG) { // FIXME add config
            Timber.plant(DebugTree())
        }
    }

    override fun v(message: String) {
        Timber.v(message)
    }

    override fun d(message: String) {
        Timber.d(message)
    }

    override fun w(message: String) {
        Timber.w(message)
    }

    override fun e(message: String) {
        Timber.e(message)
    }

    override fun e(throwable: Throwable) {
        Timber.e(throwable)
    }
}