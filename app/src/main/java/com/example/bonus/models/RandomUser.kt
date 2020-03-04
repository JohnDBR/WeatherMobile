package com.example.bonus.models

import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class RandomUser {
    var gender: String? = null
    var name: Name? = null
    var email : String? = null
    var picture : Picture? = null
    var phone : String? = null


    // There are some missing classes...

    class Name {
        var title: String? = null
        var first: String? = null
        var last: String? = null
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
        fun getUser(response: JSONObject): ArrayList<RandomUser> {
            val list = ArrayList<RandomUser>()
            try {
                val info = response.getJSONArray("results")
                for (i in 0 until info.length()) {
                    val persona = info.getJSONObject(i).toString()
                    val temp =
                        g.fromJson(persona, RandomUser::class.java)
                    list.add(temp)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return list
        }
    }
}