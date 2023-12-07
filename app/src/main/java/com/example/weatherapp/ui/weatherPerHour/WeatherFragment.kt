package com.example.weatherapp.ui.weatherPerHour

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherapp.data.forecastmodel.Forecast
import com.example.weatherapp.data.forecastmodel.ForecastData
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: WeatherAdapter
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weatherLiveData.observe(viewLifecycleOwner) { weather ->
            updateWeather(weather)
            Log.i("shidou", "$weather : success")
            navToSevenDayForecast(weather)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            showError(error)
        }
        viewModel.getWeather()

        viewModel.forecastLiveData.observe(viewLifecycleOwner) { forecast ->
            updateForecast(forecast)
        }

        viewModel.getForecast()

    }

    private fun updateForecast(forecast: List<ForecastData>?) {
        adapter = WeatherAdapter(emptyList())
        binding.rvWeatherPerHour.adapter = adapter
        adapter.updateData(forecast!!)
    }

    private fun showError(error: String?) {
    }

    private fun navToSevenDayForecast(weather: CurrentWeather?) {
        binding.imgPlus.setOnClickListener {
            val action =
                WeatherFragmentDirections.actionWeatherFragmentToSevenDaysForecastFragment(weather!!)
            findNavController().navigate(action)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateWeather(weather: CurrentWeather?) {
        val iconId = weather?.weather?.get(0)?.icon
        val imgUrl = "https://openweathermap.org/img/w/$iconId.png"
        Picasso.get().load(imgUrl).into(binding.imgRainyDay)

        val date = convertDateFromTimeStamp(weather?.dt!!)

        binding.apply {
            tvTemp.text = "${weather.main?.temp.toString()}°C"
            Log.d("shidou", weather.main?.temp.toString())
            tvMaxTemp.text = "Max: ${weather.main?.temp_max.toString()}°C"
            tvMinTemp.text = "Min: ${weather.main?.temp_min.toString()}°C"
            tvWeather.text = weather.weather?.get(0)?.description
            tvDate.text = date
        }
    }

    private fun convertDateFromTimeStamp(timestamp: Int): String {
        val date = Date(timestamp.toLong() * 1000)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val calendar = Calendar.getInstance()
        calendar.time = date
        return dateFormat.format(calendar.time)
    }

}