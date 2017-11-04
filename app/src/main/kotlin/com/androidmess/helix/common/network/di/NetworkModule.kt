package com.androidmess.helix.common.network.di

import com.androidmess.helix.common.app.AppConfig
import com.androidmess.helix.common.network.NetworkConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return NetworkConfig()
    }

    @Provides
    @Singleton
    fun provideOkHttp(appConfig: AppConfig): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val levelBody = HttpLoggingInterceptor.Level.BODY
        val levelNone = HttpLoggingInterceptor.Level.NONE
        logging.level = if (appConfig.isDebug) levelBody else levelNone
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okClient: OkHttpClient, networkConfig: NetworkConfig): Retrofit {
        return Retrofit.Builder()
                .baseUrl(networkConfig.baseUrl)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}