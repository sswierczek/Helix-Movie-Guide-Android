package com.androidmess.helix

import com.androidmess.helix.common.rx.SchedulersInjector
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class BaseTest {

    private val testScheduler = Schedulers.trampoline()

    val testSchedulers: SchedulersInjector = object : SchedulersInjector {
        override fun ui(): Scheduler {
            return testScheduler
        }

        override fun io(): Scheduler {
            return testScheduler
        }

        override fun computation(): Scheduler {
            return testScheduler
        }
    }
}
