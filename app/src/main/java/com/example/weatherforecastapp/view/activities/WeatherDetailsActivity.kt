package com.example.weatherforecastapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.data.DetailedWeather
import com.example.weatherforecastapp.databinding.ActivityWeatherDetailsBinding

class WeatherDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityWeatherDetailsBinding
    var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_details)

        intent.extras?.getParcelable<DetailedWeather>("weather")?.let {
            binding.weather = it
        }
        city = intent.extras?.getString("city") ?: ""
        supportActionBar?.let {
            it.title = city
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}