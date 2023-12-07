package com.example.weatherapp.data.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Sys(
    @SerialName("country")
    val country: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("sunrise")
    val sunrise: Int?,
    @SerialName("sunset")
    val sunset: Int?,
    @SerialName("type")
    val type: Int?
) : Parcelable