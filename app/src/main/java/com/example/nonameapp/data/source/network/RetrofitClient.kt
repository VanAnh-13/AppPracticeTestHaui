package com.example.nonameapp.data.source.network

import com.example.nonameapp.utils.constant.APIConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    @Volatile
    private var INSTANCE: Retrofit? = null

    val apiService: ApiService by lazy {
        getInstance().create(ApiService::class.java)
    }
    fun  getInstance(): Retrofit = INSTANCE ?: synchronized(this){
        val instant = retrofitBuilder()
        INSTANCE = instant
        instant
    }

    private fun retrofitBuilder(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(APIConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideOkHttpClient() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(APIConstant.TimeOut.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        return okHttpClient.build()
    }
}