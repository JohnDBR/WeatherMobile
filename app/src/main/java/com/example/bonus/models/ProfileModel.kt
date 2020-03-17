package com.example.classvideos.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProfileModel (
    val  name: String,
    val photo: Int,
    val photoUrl: String

) : Parcelable {
}