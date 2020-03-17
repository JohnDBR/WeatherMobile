package com.example.bonus.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CityViewModel (application: Application) : AndroidViewModel(application) {

        private var cityDao : CityDao

        init {
            cityDao = CityDao.getInstance(this.getApplication())
        }

        fun addUser(){
            cityDao.addUser()
        }

        fun getUsers() : MutableLiveData<List<GetFirstWeather>> {
            return cityDao.getUsers();
        }
}