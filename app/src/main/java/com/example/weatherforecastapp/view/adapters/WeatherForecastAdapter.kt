package com.example.weatherforecastapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.weatherforecastapp.data.DetailedWeather
import com.example.weatherforecastapp.databinding.ItemWeatherForecastBinding
import com.example.weatherforecastapp.view.viewholder.WeatherForecastViewHolder

class WeatherForecastAdapter(val list: List<DetailedWeather>):
Adapter<WeatherForecastViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherForecastBinding.inflate(inflater, parent, false)
        return WeatherForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.binding.weather = list[position]
        holder.itemView.setOnClickListener {
            if(this::onItemSelected.isInitialized) {
                onItemSelected(list[position])
            }
        }
    }

    override fun getItemCount() = list.size

    private lateinit var onItemSelected: (DetailedWeather) -> Unit

    fun setOnItemSelectedListener(listener: (DetailedWeather) -> Unit) {
        onItemSelected = listener
    }
}