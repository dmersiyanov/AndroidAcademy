package com.dmity.androidacademy.features.newsList.model

import java.io.Serializable
import java.util.*

abstract class GenericNewsItem(val title: String,
                               val imageUrl: String,
                               val newsCategory: String,
                               val publishDate: Date,
                               val previewText: String,
                               val fullText: String): Serializable, DisplayableItem