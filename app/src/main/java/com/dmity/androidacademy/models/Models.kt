package com.dmity.androidacademy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class NewsItem(val title: String,
               val imageUrl: String,
               val category: Category,
               val publishDate: Date,
               val previewText: String,
               val fullText: String): Parcelable

@Parcelize
class Category(val id: Int,
               val name: String) : Parcelable