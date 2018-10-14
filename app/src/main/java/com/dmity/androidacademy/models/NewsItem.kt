package com.dmity.androidacademy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
open class NewsItem(val title: String = "",
               val imageUrl: String = "",
               val newsCategory: Categories? = null,
               val publishDate: Date = Date(),
               val previewText: String = "",
               val fullText: String = ""): Parcelable, DisplayableItem

