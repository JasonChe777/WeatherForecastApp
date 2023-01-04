package com.example.weatherforecastapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.data.Repository
import com.example.weatherforecastapp.data.response.ForecastResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WeatherListViewModel(private val repository: Repository): ViewModel() {
    val weather: LiveData<ForecastResponse> = repository.weatherResponse
    val error: LiveData<String> = repository.error

    fun getForecast(city: String) {
        viewModelScope.launch(IO+eh) {
            repository.getWeatherForecast(city)
        }
    }

    val eh = CoroutineExceptionHandler { coroutineContext, throwable ->
        repository.error.postValue("Something went wrong. Please retry.")
    }
}