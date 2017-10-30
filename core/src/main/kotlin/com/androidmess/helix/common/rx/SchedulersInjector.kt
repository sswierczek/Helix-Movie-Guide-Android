package com.androidmess.helix.common.rx

import io.reactivex.Scheduler

/**
 * Abstraction for Schedulers for easier testing.
 *
 * Rx plugins usage seems to be too implicit and buggy.
 * Rationale here https://medium.com/@peter.tackage/an-alternative-to-rxandroidplugins-and-rxjavaplugins-scheduler-injection-9831bbc3dfaf
 */
interface SchedulersInjector {

    fun ui(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler
}