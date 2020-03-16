package com.example.bonus.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.bonus.R
import com.example.bonus.models.ProfileModel
import org.json.JSONObject

class RandomUserDao private constructor(var context: Context) {

    private val userList = mutableListOf<RandomUser>()
    private val users = MutableLiveData<List<RandomUser>>()
    private var queue : RequestQueue

    init {
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object {
        @Volatile
        private var INSTANCE: RandomUserDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: RandomUserDao(context).also { INSTANCE = it }
            }
    }

    fun addUser() {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObjectRequest())
    }

    fun getUsers() : MutableLiveData<List<RandomUser>> {
        return users;
    }

    fun getJsonObjectRequest() : JsonObjectRequest {

        val url =  "https://randomuser.me/api/?results=20"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
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

    fun parseObjectG(response: JSONObject) {
        var list = RandomUser.getUser(response)
        for (element in list) {
            Log.d("VideoVolleyLiveData",  "element "+element.name?.first)
            userList.add(element)
        }
        users.value = userList
        // for (element in list) {
        //     Log.d("WebJson", "parseObjectG " + element.name?.first)
        //     users.add(
        //         ProfileModel(element.name!!.first.toString(),element.name!!.last.toString(),
        //             R.drawable.banana,element.picture!!.large.toString(),element.email.toString(),element.phone.toString())
        //     )
        //}
        //adapter!!.notifyDataSetChanged()
    }

}