package com.androidmess.helix.common.debug

import com.androidmess.helix.common.app.AppConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class TimberL(private val appConfig: AppConfig) : L {

    override fun init() {
        if (appConfig.isDebug) {
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
