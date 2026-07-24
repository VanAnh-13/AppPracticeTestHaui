package com.example.nonameapp.data.source.network;

import com.example.nonameapp.utils.constant.APIConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static volatile Retrofit instance;

    private RetrofitClient() {}

    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = retrofitBuilder();
                }
            }
        }
        return instance;
    }

    private static Retrofit retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(APIConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(APIConstant.TimeOut.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor);
        return okHttpClient.build();
    }
}
