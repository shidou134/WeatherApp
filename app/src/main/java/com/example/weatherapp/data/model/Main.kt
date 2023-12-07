package com.example.weatherapp.data.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Main(
    @SerialName("feels_like")
    val feels_like: Double?,
    @SerialName("grnd_level")
    val grnd_level: Int?,
    @SerialName("humidity")
    val humidity: Int?,
    @SerialName("pressure")
    val pressure: Int?,
    @SerialName("sea_level")
    val sea_level: Int?,
    @SerialName("temp")
    val temp: Int?,
    @SerialName("temp_max")
    val temp_max: Int?,
    @SerialName("temp_min")
    val temp_min: Int?
):Parcelable