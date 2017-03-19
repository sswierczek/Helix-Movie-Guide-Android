package com.androidmess.helix

import org.junit.Before
import org.junit.Test

class ExampleTest {

    var example: Example? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        example = Example()
    }

    @Test
    fun exampleTest() {
        example?.exampleMethod()
    }
}