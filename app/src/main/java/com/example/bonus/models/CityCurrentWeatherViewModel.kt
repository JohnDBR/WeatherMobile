package com.example.bonus.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CityCurrentWeatherViewModel (application: Application) : AndroidViewModel(application) {

        private var cityCurrentWeatherDao : CityCurrentWeatherDao

        init {
            cityCurrentWeatherDao = CityCurrentWeatherDao.getInstance(this.getApplication())
        }

        fun addUser(city : String = "Barranquilla"){
            cityCurrentWeatherDao.addUser(city)
        }

        fun getUsers() : MutableLiveData<List<CityCurrentWeatherModel>> {
            return cityCurrentWeatherDao.getUsers();
        }
}