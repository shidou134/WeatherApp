package com.example.weatherapp.data.forecastmodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    @SerialName("pod")
    val pod: String?
)