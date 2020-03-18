package com.example.bonus.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class CityForecastWeatherDao private constructor(var context: Context) {

    private val cities = MutableLiveData<List<CityForecastWeatherModel>>()

    companion object {
        @Volatile
        private var INSTANCE: CityForecastWeatherDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: CityForecastWeatherDao(context).also { INSTANCE = it }
            }
    }

    fun addCity(city : String = "Barranquilla") {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObjectRequest(city))
    }

    fun getCityForecast() : MutableLiveData<List<CityForecastWeatherModel>> {
        return cities;
    }

    fun getJsonObjectRequest(city : String = "Barranquilla") : JsonObjectRequest {

        val url =
            "https://api.openweathermap.org/data/2.5/forecast?q=$city&appid=f7d96811a87ec0449c105411e58cabbd&units=metric"

        return JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //parseObject(response)
                parseObjectG(response)
            },
            Response.ErrorListener{
                Log.d("WebJson", "ERROR")
                Log.d("WebJson", it.message)

            }
        )
    }

    fun parseObjectG(response: JSONObject) {
        CityForecastWeatherModel.getCity(response)?.let {
            cities.value = it
        }
    }

}