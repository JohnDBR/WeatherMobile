package com.example.bonus.models

import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class GetFirstWeather {

    var name: String? = null
    var picture : Picture? = null
    var feels_like : main? = null
    var temp : main? = null



    // There are some missing classes...

    class main {
        var temp: String? = null
        var feels_like: String? = null
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    class  Picture{
        var large : String? =null
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    // ArrayList of RandomUser elements
    companion object {
        var g = Gson()
        fun getCity(response: JSONObject): ArrayList<GetFirstWeather> {
            val list = ArrayList<GetFirstWeather>()
            try {
                val info = response.getJSONArray("results")
                for (i in 0 until info.length()) {
                    val ciudad = info.getJSONObject(i).toString()
                    val tempo =
                        g.fromJson(ciudad, GetFirstWeather::class.java)
                    list.add(tempo)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return list
        }
    }
}