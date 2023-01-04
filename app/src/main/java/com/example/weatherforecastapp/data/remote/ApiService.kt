package com.example.weatherforecastapp.data.remote

import com.example.weatherforecastapp.data.Constants
import com.example.weatherforecastapp.data.response.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city:String,
        @Query("appid") appid:String = Constants.API_KEY,
        @Query("units") units: String = Constants.UNITS_IMPERIAL
    ):Response<ForecastResponse>

    companion object {
        val INSTANCE: ApiService = RetrofitClient.retrofit.create(ApiService::class.java)
    }
}