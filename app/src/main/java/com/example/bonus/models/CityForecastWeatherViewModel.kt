package com.example.bonus.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CityForecastWeatherViewModel (application: Application) : AndroidViewModel(application) {

        private var cityForecastWeatherDao : CityForecastWeatherDao

        init {
            cityForecastWeatherDao = CityForecastWeatherDao.getInstance(this.getApplication())
        }

        fun addCity(city : String = "Barranquilla"){
            cityForecastWeatherDao.addCity(city)
        }

        fun getCityForecast() : MutableLiveData<List<CityForecastWeatherModel>> {
            return cityForecastWeatherDao.getCityForecast();
        }
}