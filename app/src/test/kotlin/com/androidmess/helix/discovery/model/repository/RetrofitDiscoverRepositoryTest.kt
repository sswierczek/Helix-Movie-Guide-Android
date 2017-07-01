package com.androidmess.helix.discovery.model.repository

import com.androidmess.helix.BaseTest
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit

@Suppress("IllegalIdentifier")
class RetrofitDiscoverRepositoryTest : BaseTest() {

    val TEST_API_KEY = "test_api_key1234"

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var retrofitService: RetrofitDiscoverService

    lateinit var repository: RetrofitDiscoverRepository

    override fun setUp() {
        super.setUp()
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