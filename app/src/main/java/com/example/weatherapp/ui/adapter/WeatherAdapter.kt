package com.example.weatherapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.FocusFinder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.forecastmodel.Forecast
import com.example.weatherapp.data.forecastmodel.ForecastData
import com.example.weatherapp.databinding.ItemLayoutPerDayBinding
import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.databinding.ItemLayoutPerHourBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherAdapter(private var list: List<ForecastData>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private var binding: ItemLayoutPerHourBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForecastData) {
            val iconId = item.weather?.get(0)?.icon
            val imgUrl = "https://openweathermap.org/img/w/$iconId.png"
            Picasso.get().load(imgUrl).into(binding.imgRainyDay)
            binding.apply {
                tvHour.text = displayTime(item.dt_txt)
                tvTempPerHour.text = "${item.main?.temp.toString()}°C"
            }
        }

        private fun displayTime(dtTxt: String?): CharSequence? {
            val input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val output = DateTimeFormatter.ofPattern("MM-dd HH:mm")
            val dateTime = LocalDateTime.parse(dtTxt, input)
            return output.format(dateTime)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding =
            ItemLayoutPerHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<ForecastData>) {
        list = data
        notifyDataSetChanged()
    }
}

