package com.example.weatherapp.data.forecastmodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
    val temp: Double?,
    @SerialName("temp_kf")
    val temp_kf: Double?,
    @SerialName("temp_max")
    val temp_max: Double?,
    @SerialName("temp_min")
    val temp_min: Double?
)