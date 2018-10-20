package com.dmity.androidacademy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class NewsItem(val title: String,
               val imageUrl: String,
               val newsCategory: Categories,
               val publishDate: Date,
               val previewText: String,
               val fullText: String): Parcelable, DisplayableItem

