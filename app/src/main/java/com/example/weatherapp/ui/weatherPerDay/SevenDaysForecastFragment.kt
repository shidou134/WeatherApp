package com.example.weatherapp.ui.weatherPerDay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSevenDaysForecastBinding
import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*


class SevenDaysForecastFragment : Fragment() {

    private lateinit var binding: FragmentSevenDaysForecastBinding
    private val args: SevenDaysForecastFragmentArgs by navArgs()
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSevenDaysForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val temp = args.temp

        viewModel.weatherLiveData.observe(viewLifecycleOwner) { weather ->
            updateWeather(weather)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            showError(error)
        }
        viewModel.getWeather()

        binding.apply {
            tvMaxTemp.text = "Max: ${temp.main?.temp_max.toString()}°C"
            tvMinTemp.text = "Min: ${temp.main?.temp_min.toString()}°C"
            tvLocation.text = temp.name
        }
        initAction()
    }

    private fun showError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    private fun updateWeather(weather: CurrentWeather) {
        val sunrise = convertTimestamp(weather.sys?.sunrise)
        val sunset = convertTimestamp(weather.sys?.sunset)
        binding.apply {
            tvTimeSunrise.text = "$sunrise"
            tvTimeSunset.text = "$sunset"
            tvWindSpeed.text = "${weather.wind?.speed.toString()} Km/h"
            tvPressureLevel.text = "${weather.main?.pressure.toString()} hPa"
            tvHumidityLevel.text = "${weather.main?.humidity.toString()}%"
            tvSeaLevel.text = "${weather.main?.sea_level.toString()} hPa"
        }
    }

    private fun convertTimestamp(timestamp: Int?): String {
        val date = Date((timestamp ?: 0).toLong() * 1000)
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun initAction() {
        binding.imgMenu.setOnClickListener {
            findNavController().navigate(R.id.action_sevenDaysForecastFragment_to_weatherFragment)
        }
    }

}


