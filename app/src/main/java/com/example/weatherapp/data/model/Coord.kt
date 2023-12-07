package com.example.weatherapp.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Coord(
    @SerialName("lat")
    val lat: Double?,
    @SerialName("lon")
    val lon: Double?
) : Parcelable