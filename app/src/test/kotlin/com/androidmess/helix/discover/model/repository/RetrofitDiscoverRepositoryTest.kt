package com.androidmess.helix.discover.model.repository

import com.androidmess.helix.common.network.NetworkConfig
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit

val TEST_API_KEY = "test_api_key1234"

class RetrofitDiscoverRepositoryTest {

    val retrofitService = mockk<RetrofitDiscoverService>(relaxed = true)
    val retrofit = mockk<Retrofit> {
        every { create(RetrofitDiscoverService::class.java) } returns retrofitService
    }
    val networkConfig = mockk<NetworkConfig> {
        every { apiKey } returns TEST_API_KEY
    }

    val repository = RetrofitDiscoverRepository(retrofit, networkConfig)

    @Test
    fun `When discover movies request called should use page with given api key`() {
        val testPage = 1

        runBlocking { repository.discover(testPage) }

        coVerify { retrofitService.discoverMovies(TEST_API_KEY, testPage) }
    }
}
