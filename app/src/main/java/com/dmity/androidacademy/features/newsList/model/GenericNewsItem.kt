package com.dmity.androidacademy.features.newsList.model

import java.io.Serializable

abstract class GenericNewsItem(val title: String,
                               val imageUrl: String,
                               val newsCategory: String,
                               val publishDate: String,
                               val fullText: String): Serializable, DisplayableItem