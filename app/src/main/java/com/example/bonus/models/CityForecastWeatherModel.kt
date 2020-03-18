package com.example.bonus.models

import android.os.Parcelable
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import org.json.JSONObject

@Parcelize
class CityForecastWeatherModel(
    var dt : String,
    var main : Main,
    var weather : List<WheaterElement>,
    var clouds : Cloud,
    var visibility : String,
    var wind : Wind,
    // var rain : Rain,
    var sys : Sys,
    var dt_txt : String
): Parcelable {

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
        var sea_level: String,
        var grnd_level: String,
        var humidity: String,
        var temp_kf: String
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
        var pod: String
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    //@Parcelize
    //class Rain(
    //    var 3h: String
    //): Parcelable {
    //    override fun toString(): String {
    //        return g.toJson(this)
    //    }
    //}

    // Single RandomUser element
    companion object {
        var g = Gson()
        fun getCity(response: JSONObject): ArrayList<CityForecastWeatherModel> {
            val list = ArrayList<CityForecastWeatherModel>()
            try {
                val info = response.getJSONArray("list")
                for (i in 0 until info.length()) {
                    val city = info.getJSONObject(i).toString()
                    val temp = g.fromJson(city, CityForecastWeatherModel::class.java)
                    list.add(temp)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return list
        }
    }

}