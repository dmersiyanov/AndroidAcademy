package com.dmity.androidacademy.features.newsList.model.mapper

import com.dmity.androidacademy.features.newsList.model.Categories
import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.dmity.androidacademy.features.newsList.model.NewsItem
import com.dmity.androidacademy.features.newsList.model.dto.NewsItemDTO
import java.util.*

class NewsItemMapper {

    fun mapToNewsItems(newsItemDTOS: List<NewsItemDTO?>): List<DisplayableItem> {
        return newsItemDTOS.mapNotNull { newsItemDTO ->
            newsItemDTO?.let { it ->
                NewsItem(
                        it.title ?: "",
                        it.url ?: "",
                        Categories.DARWIN_AWARDS,
                        it.publishDate ?: Date(),
                        it.previewText ?: "",
                        it.previewText ?: "")
            }
        }
    }
}