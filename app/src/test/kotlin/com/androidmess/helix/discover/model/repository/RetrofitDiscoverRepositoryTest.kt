package com.androidmess.helix.discover.model.repository

import com.androidmess.helix.BaseTest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

@Suppress("IllegalIdentifier", "MemberVisibilityCanPrivate")
class RetrofitDiscoverRepositoryTest : BaseTest() {

    companion object {
        const val TEST_API_KEY = "test_api_key1234"
    }

    val retrofit: Retrofit = mock()

    val retrofitService: RetrofitDiscoverService = mock()

    lateinit var repository: RetrofitDiscoverRepository

    @Before
    fun setUp() {
        whenever(retrofit.create(RetrofitDiscoverService::class.java)).thenReturn(retrofitService)
        repository = RetrofitDiscoverRepository(retrofit, TEST_API_KEY)
    }

    @Test
    fun `When discover movies request called should use page with given api key`() {
        val testPage = 1

        repository.discoverMovies(testPage)

        verify(retrofitService).discoverMovies(TEST_API_KEY, testPage)
    }
}