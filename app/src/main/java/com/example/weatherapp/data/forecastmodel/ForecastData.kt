package com.example.weatherapp.data.forecastmodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastData(
    @SerialName("clouds")
    val clouds: Clouds?,
    @SerialName("dt")
    val dt: Int?,
    @SerialName("dt_txt")
    val dt_txt: String?,
    @SerialName("main")
    val main: Main?,
    @SerialName("pop")
    val pop: Double?,
    @SerialName("rain")
    val rain: Rain?,
    @SerialName("sys")
    val sys: Sys?,
    @SerialName("visibility")
    val visibility: Int?,
    @SerialName("weather")
    val weather: List<Weather?>?,
    @SerialName("wind")
    val wind: Wind?
)