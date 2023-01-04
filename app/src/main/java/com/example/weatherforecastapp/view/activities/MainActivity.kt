package com.example.weatherforecastapp.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.commons.isConnectedToNetwork
import com.example.weatherforecastapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEvents()
    }

    private fun setupEvents() {
        binding.btnLookup.setOnClickListener {
            if(!isConnectedToNetwork()) {
                AlertDialog.Builder(this).apply {
                    setTitle(R.string.error_no_internet)
                    setMessage(R.string.error_no_internet_msg)
                    setPositiveButton(R.string.button_ok) {
                            dialog, _ ->
                        dialog.dismiss()
                    }
                }.create().show()
                return@setOnClickListener
            }

            val city = binding.edtCity.text?.trim().toString()
            if(city.isEmpty()) {
                binding.edtCity.error = getString(R.string.error_please_enter_city)
                return@setOnClickListener
            }

            val wIntent = Intent(baseContext, WeatherListActivity::class.java).apply {
                putExtra("city", city)
            }
            binding.edtCity.text?.clear()
            startActivity(wIntent)
        }
    }
}