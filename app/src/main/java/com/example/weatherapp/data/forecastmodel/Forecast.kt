package com.example.weatherapp.data.forecastmodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("city")
    val city: City?,
    @SerialName("cnt")
    val cnt: Int?,
    @SerialName("cod")
    val cod: String?,
    @SerialName("list")
    val list: List<ForecastData>?,
    @SerialName("message")
    val message: Int?
)