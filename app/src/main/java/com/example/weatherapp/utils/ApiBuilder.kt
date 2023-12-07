package com.example.weatherapp.utils

import com.example.weatherapp.data.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Utils.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}