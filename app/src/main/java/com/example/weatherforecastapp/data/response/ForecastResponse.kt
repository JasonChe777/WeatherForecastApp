package com.example.weatherforecastapp.data.response

import android.os.Parcel
import android.os.Parcelable
import com.example.weatherforecastapp.data.DetailedWeather
import com.google.gson.annotations.SerializedName

data class ForecastResponse(

    @SerializedName("cod")
    val code: String,

    @SerializedName("message")
    val msg: Int,

    @SerializedName("cnt")
    val cnt: Int,

    @SerializedName("list")
    val list: List<DetailedWeather>

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(DetailedWeather) ?: emptyList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeInt(msg)
        parcel.writeInt(cnt)
        parcel.writeTypedList(list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastResponse> {
        override fun createFromParcel(parcel: Parcel): ForecastResponse {
            return ForecastResponse(parcel)
        }

        override fun newArray(size: Int): Array<ForecastResponse?> {
            return arrayOfNulls(size)
        }
    }
}
