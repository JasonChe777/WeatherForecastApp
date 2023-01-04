package com.example.weatherforecastapp.commons

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast


fun Context.isConnectedToNetwork(): Boolean {
    val connectManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetworkInfo = connectManager.activeNetwork
        if (activeNetworkInfo != null) { // connected to the internet
            return true
        }
    } else {
        val activeNetworkInfo = connectManager.activeNetworkInfo
        if (activeNetworkInfo != null)
        return true
    }
    return false
}
