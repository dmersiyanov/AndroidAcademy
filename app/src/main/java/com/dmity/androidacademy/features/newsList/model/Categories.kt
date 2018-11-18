package com.dmity.androidacademy.features.newsList.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Categories(val id: Int,
                      val category: String) : Parcelable {

    DARWIN_AWARDS(1, "Darwin Awards"),
    CRIMINAL(2, "Criminal"),
    ANIMALS(3, "Animals"),
    MUSIC(4, "Music"),

}