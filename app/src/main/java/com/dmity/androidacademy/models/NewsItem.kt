package com.dmity.androidacademy.models

import java.util.*


class NewsItem(title: String,
               imageUrl: String,
               newsCategory: Categories,
               publishDate: Date,
               previewText: String,
               fullText: String)
    : GenericNewsItem(title, imageUrl, newsCategory, publishDate, previewText, fullText), DisplayableItem

