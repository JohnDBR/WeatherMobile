package com.example.bonus.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class CityCurrentWeatherDao private constructor(var context: Context) {

    //private val userList = mutableListOf<CityCurrentWeatherModel>()
    private val cities = MutableLiveData<List<CityCurrentWeatherModel>>()
    //private var queue : RequestQueue = VolleySingleton.getInstance(context).requestQueue

    companion object {
        @Volatile
        private var INSTANCE: CityCurrentWeatherDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: CityCurrentWeatherDao(context).also { INSTANCE = it }
            }
    }

    fun addCity(city : String = "Barranquilla") {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObjectRequest(city))
    }

    fun getCities() : MutableLiveData<List<CityCurrentWeatherModel>> {
        return cities;
    }

    fun getJsonObjectRequest(city : String = "Barranquilla") : JsonObjectRequest {

        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=f7d96811a87ec0449c105411e58cabbd&units=metric"

        return JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //parseObject(response)
                parseObjectG(response, city)
            },
            Response.ErrorListener{
                Log.d("WebJson", "ERROR")
                Log.d("WebJson", it.message)

            }
        )
    }

    fun parseObjectG(response: JSONObject, city: String) {
        //var list = CityCurrentWeatherModel.getUser(response)
        //for (element in list) {
        //    Log.d("VideoVolleyLiveData",  "element "+element.name?.first)
        //    userList.add(element)
        //}
        //users.value = CityCurrentWeatherModel.getUser(response) //userList
        CityCurrentWeatherModel.getCity(response)?.let {
            val cityList = mutableListOf<CityCurrentWeatherModel>()
            if (cities.value != null) {
                cityList.addAll(cities.value!!)
            }
            it.hard_name = city
            cityList.add(it)
            //val weatherList = it.weather
            Log.d("VideoVolleyLiveData",  "element "+it.weather.get(0).id)
            cities.value = cityList
        }
        // for (element in list) {
        //     Log.d("WebJson", "parseObjectG " + element.name?.first)
        //     users.add(
        //         CityCurrentWeatherModel(element.name!!.first.toString(),element.name!!.last.toString(),
        //             R.drawable.banana,element.picture!!.large.toString(),element.email.toString(),element.phone.toString())
        //     )
        //}
        //adapter!!.notifyDataSetChanged()
    }

}