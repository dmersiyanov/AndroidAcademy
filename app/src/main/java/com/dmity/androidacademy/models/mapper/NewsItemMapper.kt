package com.dmity.androidacademy.models.mapper

import com.dmity.androidacademy.models.Categories
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.NewsItem
import com.dmity.androidacademy.models.dto.NewsItemDTO
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