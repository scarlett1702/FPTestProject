package com.test.fptestproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.fptestproject.R
import com.test.fptestproject.data.ListData
import com.test.fptestproject.data.WeatherData
import com.test.fptestproject.databinding.ActivityMainBinding
import com.test.fptestproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

     private lateinit var mainViewModel: MainViewModel
     private lateinit var adapter: WeatherViewAdapter


    private var totalList: ArrayList<ListData> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getWeatherData()

        setAdapter()

        mainViewModel.seoulWeatherLiveData.observe(this, Observer {
            val header = WeatherData(it.title, it.consolidated_weather)
            val list = ArrayList<ListData>()
            list.add(0, header)
            list.addAll(it.consolidated_weather)

            totalList.addAll(list)
            adapter.setItem(totalList)
        })

        mainViewModel.londonWeatherLiveData.observe(this, Observer {
            val header = WeatherData(it.title, it.consolidated_weather)
            val list = ArrayList<ListData>()
            list.add(0, header)
            list.addAll(it.consolidated_weather)

            totalList.addAll(list)
            adapter.setItem(totalList)
        })

        mainViewModel.chicagoWeatherLiveData.observe(this, Observer {
            val header = WeatherData(it.title, it.consolidated_weather)
            val list = ArrayList<ListData>()
            list.add(0, header)
            list.addAll(it.consolidated_weather)

            totalList.addAll(list)
            adapter.setItem(totalList)
        })
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(this)
        adapter = WeatherViewAdapter(arrayListOf())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

}