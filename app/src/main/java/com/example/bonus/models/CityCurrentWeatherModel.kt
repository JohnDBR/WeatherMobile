package com.example.bonus.models

import android.os.Parcelable
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import org.json.JSONObject

@Parcelize
class CityCurrentWeatherModel(
    var coord: Coordinate,
    var weather : List<WheaterElement>,
    var base : String,
    var main : Main,
    var visibility : String,
    var wind : Wind,
    var clouds : Cloud,
    var dt : String,
    var sys : Sys,
    var timezone : String,
    var id : String,
    var name : String,
    var cod : String
): Parcelable {

    @Parcelize
    class Coordinate(
        var lon: String,
        var lat: String
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    @Parcelize
    class WheaterElement(
        var id: String,
        var main: String,
        var description: String,
        var icon: String
        ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    @Parcelize
    class Main(
        var temp: String,
        var feels_like: String,
        var temp_min: String,
        var temp_max: String,
        var pressure: String,
        var humidity: String
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    @Parcelize
    class Wind(
        var speed: String,
        var deg: String
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    @Parcelize
    class Cloud(
        var all: String
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    @Parcelize
    class Sys(
        var type: String,
        var id: String,
        var country: String,
        var sunrise: String,
        var sunset: String
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    // Single RandomUser element
    companion object {
        var g = Gson()
        fun getCity(response: JSONObject): CityCurrentWeatherModel? {//ArrayList<CityCurrentWeatherModel> {
            //val list = ArrayList<CityCurrentWeatherModel>()
            var element : CityCurrentWeatherModel? = null
            try {
                //val info = response.getJSONArray("results")
                //for (i in 0 until info.length()) {
                    //val persona = info.getJSONObject(i).toString()
                    //val temp =
                        //g.fromJson(persona, CityCurrentWeatherModel::class.java)
                    //list.add(temp)
                //}
                Log.d("VideoVolleyLiveData",  response.toString())
                element = g.fromJson(response.toString(), CityCurrentWeatherModel::class.java)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return element //list
        }
    }

}