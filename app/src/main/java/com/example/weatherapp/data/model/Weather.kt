package com.example.weatherapp.data.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Weather(
    @SerialName("description")
    val description: String?,
    @SerialName("icon")
    val icon: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("main")
    val main: String?
) : Parcelable