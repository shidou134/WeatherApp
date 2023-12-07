package com.example.weatherapp.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Clouds(
    @SerialName("all")
    val all: Int?
) : Parcelable