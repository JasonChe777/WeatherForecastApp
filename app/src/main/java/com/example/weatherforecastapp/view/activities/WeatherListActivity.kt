package com.example.weatherforecastapp.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecastapp.data.Repository
import com.example.weatherforecastapp.data.remote.ApiService
import com.example.weatherforecastapp.databinding.ActivityWeatherListBinding
import com.example.weatherforecastapp.view.adapters.WeatherForecastAdapter
import com.example.weatherforecastapp.viewmodel.WeatherListVMFactory
import com.example.weatherforecastapp.viewmodel.WeatherListViewModel

class WeatherListActivity : AppCompatActivity() {
    lateinit var binding: ActivityWeatherListBinding
    lateinit var viewModel: WeatherListViewModel
    lateinit var adapter: WeatherForecastAdapter

    var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvForecast.layoutManager = LinearLayoutManager(baseContext)
        setupViewModel()
        setupObservers()

        city = intent.extras?.getString("city") ?: ""

        supportActionBar?.title = (city)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getForecast(city)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModel() {
        val repository = Repository(ApiService.INSTANCE)
        val factory = WeatherListVMFactory(repository)
        viewModel = ViewModelProvider(this, factory)[WeatherListViewModel::class.java]
    }

    private fun setupObservers() {
        viewModel.weather.observe(this) {
            adapter = WeatherForecastAdapter(it.list)
            binding.rvForecast.adapter = adapter
            adapter.setOnItemSelectedListener {
                val wdIntent = Intent(baseContext, WeatherDetailsActivity::class.java).apply {
                    putExtra("weather", it)
                    putExtra("city", city)
                }
                startActivity(wdIntent)
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
        }
    }
}