package com.test.fptestproject.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.fptestproject.data.ConsolidatedWeatherData
import com.test.fptestproject.data.ListData
import com.test.fptestproject.data.WeatherData
import com.test.fptestproject.databinding.ItemHeaderBinding
import com.test.fptestproject.databinding.ItemWeatherBinding
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class WeatherViewAdapter @Inject constructor(private var list: ArrayList<ListData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == ListData.TYPE_HEADER) {
            val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HeaderViewHolder(parent.context, binding)
        } else {
            val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemViewHolder(parent.context, binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            val header: WeatherData = list[position] as WeatherData
            holder.bind(header.title)
        } else {
            val item: ConsolidatedWeatherData = list[position] as ConsolidatedWeatherData
            (holder as ItemViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItem(list: ArrayList<ListData>) {
        this.list = list

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].getItemType() == 0) {
            ListData.TYPE_HEADER
        } else {
            ListData.TYPE_ITEM
        }
    }

    inner class ItemViewHolder(val context: Context, val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ConsolidatedWeatherData) {
            Glide.with(context)
                .load("https://www.metaweather.com/static/img/weather/png/"+item.weather_state_abbr+".png")
                .override(150, 150)
                .into(binding.stateImage)
            try {
                val format = SimpleDateFormat("EEE dd MMM")
                val calendar = Calendar.getInstance()
                val currentDate: String = format.format(calendar.time)
                calendar.add(Calendar.DATE, 1)
                val tomorrowDate: String = format.format(calendar.time)

                val date = SimpleDateFormat("yyyy-MM-dd").parse(item.applicable_date)
                val formatDate: String = format.format(date)

                if (currentDate == formatDate) {
                    binding.date.text = "TODAY"
                } else if (tomorrowDate == formatDate) {
                    binding.date.text = "TOMORROW"
                } else {
                    binding.date.text = formatDate
                }
            } catch (e: Exception) {
                e.printStackTrace()
                binding.date.text = item.applicable_date
            }
            binding.data = item
        }
    }

    inner class HeaderViewHolder(val context: Context, val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.city.text = title
        }
    }


}