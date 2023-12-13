package com.example.weatherapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.weatherapp.data.forecastmodel.ForecastData
import com.example.weatherapp.utils.ApiBuilder
import com.example.weatherapp.data.model.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class WeatherViewModel(private val context: Context) : ViewModel() {
    private val _weatherLiveData: MutableLiveData<CurrentWeather> = MutableLiveData()
    val weatherLiveData: LiveData<CurrentWeather>
        get() = _weatherLiveData
    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error
    private val _forecastLiveData: MutableLiveData<List<ForecastData>> = MutableLiveData()
    val forecastLiveData: LiveData<List<ForecastData>>
        get() = _forecastLiveData

    fun getForecast() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                ApiBuilder.api.getForecast(
                    city = city,
                    apiKey = apiKey,
                    units = units
                )
            } catch (e: HttpException) {
                _error.postValue("http error: ${e.message}")
                return@launch
            } catch (e: IOException) {
                _error.postValue("IO error: ${e.message}")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()!!
                    _forecastLiveData.value = data.list!!
                }

            }
        }
    }

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                ApiBuilder.api.getWeather(
                    city = "ha noi",
                    apiKey = "71ac2999eb0cdd9b712b1e70fc0fe448",
                    units = "metric"
                )
            } catch (e: HttpException) {
                _error.postValue("http error: ${e.message}")
                return@launch
            } catch (e: IOException) {
                _error.postValue("IO error: ${e.message}")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()!!
                    _weatherLiveData.value = data
                    Log.d("shidou", "getTemp: success ")
                }
            }
        }
    }

    companion object WeatherConstants {
        const val city = "ha noi"
        const val apiKey = "71ac2999eb0cdd9b712b1e70fc0fe448"
        const val units = "metric"
    }
}