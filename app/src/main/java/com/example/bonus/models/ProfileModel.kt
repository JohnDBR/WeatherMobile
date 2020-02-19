package com.example.classvideos.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProfileModel (
    val first_name: String,
    val last_name: String,
    val photo: Int //String
) : Parcelable {
}