package com.ankit.tmdblistdemo.di

import com.ankit.tmdblistdemo.BuildConfig
import com.ankit.tmdblistdemo.network.GetData
import com.ankit.tmdblistdemo.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideApiService(): GetData {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideNetworkClient())
            .build()
            .create(GetData::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()
    }
}