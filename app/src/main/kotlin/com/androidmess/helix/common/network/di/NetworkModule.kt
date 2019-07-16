package com.androidmess.helix.common.network.di

import com.androidmess.helix.common.app.AppConfig
import com.androidmess.helix.common.network.NetworkConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

    fun create() = module {
        single { NetworkConfig() }
        single { OkHttpClientFactory(get()).create() }
        single { RetrofitFactory(get(), get()).create() }
    }

    private class OkHttpClientFactory(private val appConfig: AppConfig) {

        fun create(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            val levelBody = HttpLoggingInterceptor.Level.BODY
            val levelNone = HttpLoggingInterceptor.Level.NONE
            logging.level = if (appConfig.isDebug) levelBody else levelNone
            return OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
        }
    }

    private class RetrofitFactory(
            private val okClient: OkHttpClient,
            private val networkConfig: NetworkConfig
    ) {
        fun create(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(networkConfig.baseUrl)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}
