package com.androidmess.helix

import org.junit.Before
import org.mockito.MockitoAnnotations

open class BaseTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}