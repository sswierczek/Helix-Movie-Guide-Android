package com.androidmess.helix.di

import com.androidmess.helix.BuildConfig
import com.androidmess.helix.common.model.repository.Repository
import com.androidmess.helix.common.rx.AppSchedulersInjector
import com.androidmess.helix.common.rx.SchedulersInjector
import com.androidmess.helix.discover.model.repository.RetrofitDiscoverRepository
import com.androidmess.helix.discover.usecase.GetDiscoverMoviesUseCase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HelixAppModule {

    @Provides
    @Singleton
    fun provideSchedulerInjector(): SchedulersInjector {
        return AppSchedulersInjector()
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL) // FIXME Add config
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    // FIXME Move repositories to separate module
    @Provides
    @Singleton
    fun providesRepositoryDiscover(retrofit: Retrofit): Repository.Discover {
        return RetrofitDiscoverRepository(retrofit, BuildConfig.API_KEY) // FIXME Add config
    }

    @Provides
    @Singleton
    fun provideGetDiscoverMoviesUseCase(repositoryDiscover: Repository.Discover): GetDiscoverMoviesUseCase {
        return GetDiscoverMoviesUseCase(repositoryDiscover)
    }
}