package com.example.bonus.models

import android.os.Parcelable
import com.example.bonus.models.RandomUser
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProfileModel (
    val first_name: String,
    val last_name: String,
    val photo: Int,
    val photoUrl: String,
    val email : String ,
    val phone : String
) : Parcelable {
}