package com.dmity.androidacademy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category(val id: Int,
               val name: String) : Parcelable