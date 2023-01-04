package com.example.weatherforecastapp.data

import androidx.lifecycle.MutableLiveData
import com.example.weatherforecastapp.data.remote.ApiService
import com.example.weatherforecastapp.data.response.ForecastResponse

class Repository(private var apiService: ApiService) {

    val weatherResponse = MutableLiveData<ForecastResponse>()
    val error = MutableLiveData<String>()


    suspend fun getWeatherForecast(city:String){

        val response = apiService.getForecast(city)
        if(!response.isSuccessful) {
            if(response.code() == 404) {
                error.postValue("Invalid city name.")
                return
            }
            error.postValue("Something went wrong. Please retry.")
            return
        }

        val forecast = response.body()

        if(forecast == null) {
            error.postValue("Empty result from server")
            return
        }

        weatherResponse.postValue(forecast)
    }
}