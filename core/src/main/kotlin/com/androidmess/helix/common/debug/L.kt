package com.androidmess.helix.common.debug

interface L {

    fun init()

    fun v(message: String)

    fun d(message: String)

    fun w(message: String)

    fun e(message: String)

    fun e(throwable: Throwable)
}
