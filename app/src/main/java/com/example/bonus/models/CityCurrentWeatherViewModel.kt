package com.example.bonus.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CityCurrentWeatherViewModel (application: Application) : AndroidViewModel(application) {

        private var cityCurrentWeatherDao : CityCurrentWeatherDao

        init {
            cityCurrentWeatherDao = CityCurrentWeatherDao.getInstance(this.getApplication())
        }

        fun addCity(city : String = "Barranquilla"){
            cityCurrentWeatherDao.addCity(city)
        }

        fun getCities() : MutableLiveData<List<CityCurrentWeatherModel>> {
            return cityCurrentWeatherDao.getCities();
        }

        fun clearCities() {
            cityCurrentWeatherDao.clearCities();
        }
}