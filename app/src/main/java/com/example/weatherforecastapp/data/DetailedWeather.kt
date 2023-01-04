package com.example.weatherforecastapp.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DetailedWeather(
    @SerializedName("main")
    val main: Main,

    @SerializedName("weather")
    val weather: List<Weather>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Main::class.java.classLoader)!!,
        parcel.createTypedArrayList(Weather) ?: emptyList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(main, flags)
        parcel.writeTypedList(weather)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailedWeather> {
        override fun createFromParcel(parcel: Parcel): DetailedWeather {
            return DetailedWeather(parcel)
        }

        override fun newArray(size: Int): Array<DetailedWeather?> {
            return arrayOfNulls(size)
        }
    }
}
