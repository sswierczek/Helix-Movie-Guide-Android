package com.androidmess.helix.common.rx

import io.reactivex.Scheduler

/**
 * Abstraction for Schedulers for easier testing.
 *
 * Rx plugins usage seems to be too implicit and buggy.
 */
interface SchedulersInjector {

    fun ui(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler
}
