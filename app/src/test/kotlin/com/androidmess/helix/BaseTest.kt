package com.androidmess.helix

import com.androidmess.helix.common.rx.SchedulersInjector
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseRxTest {

    @Mock
    lateinit var testSchedulerInjector: SchedulersInjector

    val testScheduler = Schedulers.trampoline()

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        // Used mock instead of implementation to use with InjectMocks annotation
        whenever(testSchedulerInjector.ui()).thenReturn(testScheduler)
        whenever(testSchedulerInjector.io()).thenReturn(testScheduler)
        whenever(testSchedulerInjector.computation()).thenReturn(testScheduler)
    }
}