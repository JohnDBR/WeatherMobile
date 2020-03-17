package com.example.bonus.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class CityDao private constructor(var context: Context) {

    private val CityList = mutableListOf<GetFirstWeather>()
    private val Cities = MutableLiveData<List<GetFirstWeather>>()
    private var queue : RequestQueue

    init {
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object {
        @Volatile
        private var INSTANCE: CityDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: CityDao(context).also { INSTANCE = it }
            }
    }

    val url = City()

    fun addUser() {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObjectRequest(url.barranquilla_url))
    }

    fun getUsers() : MutableLiveData<List<GetFirstWeather>> {
        return Cities;
    }



    fun getJsonObjectRequest(string: String) : JsonObjectRequest {

        val url = String

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url.toString(), null,
            Response.Listener { response ->
                //parseObject(response)
                parseObjectG(response)
            },
            Response.ErrorListener{
                Log.d("WebJson", "ERROR")

            }
        )
        return jsonObjectRequest
    }

    class City {

        val barranquilla_url = "https://fcc-weather-api.glitch.me/api/current?lat=10.9685402&lon=-74.7813187"
        val NewYork_url = "https://fcc-weather-api.glitch.me/api/current?lat=40.7142715&lon=-74.0059662"
        val Bogota_url ="https://fcc-weather-api.glitch.me/api/current?lat=4.6097102&lon=-74.081749"

    }

    fun parseObjectG(response: JSONObject) {
        var list = GetFirstWeather.getCity(response)
        for (element in list) {
            Log.d("VideoVolleyLiveData",  "element "+element.name)
            CityList.add(element)
        }
        Cities.value = CityList

    }

}