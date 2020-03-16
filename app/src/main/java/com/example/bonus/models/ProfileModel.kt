package com.example.bonus.models

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

@Parcelize
class ProfileModel( //: Parcelable {
    var gender: String,//? = null,
    var name: Name,//? = null,
    var email : String,//? = null,
    var picture : Picture,//? = null,
    var phone : String//? = null): Parcelable {
): Parcelable {

    // There are some missing classes...

    @Parcelize
    class Name( //: Parcelable {
        var title: String,//? = null,
        var first: String,//? = null,
        var last: String//? = null): Parcelable {
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    @Parcelize
    class  Picture( //: Parcelable {
        var large : String//? =null): Parcelable {
    ): Parcelable {
        override fun toString(): String {
            return g.toJson(this)
        }
    }

    // ArrayList of RandomUser elements
    companion object {
        var g = Gson()
        fun getUser(response: JSONObject): ArrayList<ProfileModel> {
            val list = ArrayList<ProfileModel>()
            try {
                val info = response.getJSONArray("results")
                for (i in 0 until info.length()) {
                    val persona = info.getJSONObject(i).toString()
                    val temp =
                        g.fromJson(persona, ProfileModel::class.java)
                    list.add(temp)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return list
        }
    }

}