package com.dmity.androidacademy.features.newsList.model


class NewsItem(title: String,
               imageUrl: String,
               newsCategory: String,
               publishDate: String,
               fullText: String)
    : GenericNewsItem(title, imageUrl, newsCategory, publishDate, fullText), DisplayableItem

