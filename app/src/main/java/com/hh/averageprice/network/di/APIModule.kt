package com.hh.averageprice.network.di

import com.hh.averageprice.repository.Repository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class APIModule constructor(baseUrl : String) {
    var baseUrl: String? =""

    init {
        this.baseUrl=baseUrl
    }

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {

        return  GsonConverterFactory.create()

    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient): Retrofit {

        return     Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRepository(): Repository {
        return Repository()
    }

}