package com.example.bonus.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class RandomUserViewModel (application: Application) : AndroidViewModel(application) {

        private var randomUserDao : RandomUserDao

        init {
            randomUserDao = RandomUserDao.getInstance(this.getApplication())
        }

        fun addUser(city : String = "Barranquilla"){
            randomUserDao.addUser(city)
        }

        fun getUsers() : MutableLiveData<List<ProfileModel>> {
            return randomUserDao.getUsers();
        }
}