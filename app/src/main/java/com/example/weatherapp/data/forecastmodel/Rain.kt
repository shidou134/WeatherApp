package com.example.weatherapp.data.forecastmodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerialName("3h")
    val h: Double?
)