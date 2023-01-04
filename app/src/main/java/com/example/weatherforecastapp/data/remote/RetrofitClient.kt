package com.example.weatherforecastapp.data.remote

import com.example.weatherforecastapp.data.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    val retrofit:Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

        val retrofit = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
        }.build()

        retrofit
    }
}