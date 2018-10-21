package com.dmity.androidacademy.models

import java.io.Serializable
import java.util.*

abstract class GenericNewsItem(val title: String,
                               val imageUrl: String,
                               val newsCategory: Categories,
                               val publishDate: Date,
                               val previewText: String,
                               val fullText: String): Serializable, DisplayableItem