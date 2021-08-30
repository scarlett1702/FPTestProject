package com.test.fptestproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.fptestproject.data.WeatherData
import com.test.fptestproject.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MainViewModel
    @Inject constructor(private val repository: WeatherRepository)
    : ViewModel() {


    private val _seoulWeatherLiveData = MutableLiveData<WeatherData>()
    val seoulWeatherLiveData : LiveData<WeatherData>
        get() = _seoulWeatherLiveData

    private val _londonWeatherLiveData = MutableLiveData<WeatherData>()
    val londonWeatherLiveData : LiveData<WeatherData>
        get() = _londonWeatherLiveData

    private val _chicagoWeatherLiveData = MutableLiveData<WeatherData>()
    val chicagoWeatherLiveData : LiveData<WeatherData>
        get() = _chicagoWeatherLiveData



    fun getWeatherData() {
        val seoulData = repository.getSeoulWeatherData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _seoulWeatherLiveData.postValue(this)
                }
            }, {
                it.printStackTrace()
            })

        val londonData = repository.getLondonWeatherData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _londonWeatherLiveData.postValue(this)
                }
            }, {
                it.printStackTrace()
            })

        val chicagoData = repository.getChicagoWeatherData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _chicagoWeatherLiveData.postValue(this)
                }
            }, {
                it.printStackTrace()
            })
    }


}