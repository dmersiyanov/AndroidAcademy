package com.dmity.androidacademy.features.newsList.model.mapper

import android.content.Context
import com.dmity.androidacademy.features.newsList.model.Categories
import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.features.newsList.model.NewsItem
import com.dmity.androidacademy.features.newsList.model.dto.NewsItemDTO
import com.dmity.androidacademy.features.newsList.model.dto.NewsResponseDTO
import com.dmity.androidacademy.utils.DateTimeUtils
import java.util.*

class NewsItemMapper {

    fun mapToNewsItems(newsItemDTOS: List<NewsItemDTO?>): List<DisplayableItem> {
        return newsItemDTOS.mapNotNull { newsItemDTO ->
            newsItemDTO?.let { it ->
                NewsItem(
                        it.title ?: "",
                        it.url ?: "",
                        Categories.EMPTY_CATEGORY.value,
                        it.publishDate ?: Date(),
                        it.previewText ?: "",
                        it.previewText ?: "")
            }
        }
    }

//    fun fromDatabase(newsEntities: List<NewsEntity?>): List<NewsItem> {
//        return newsEntities.map { it ->
//            NewsItem(
//                    it?.title ?: "",
//                    it?.imageUrl ?: "",
//                    it?.newsCategory ?: "",
//                    it?.publishDate ?: Date(),
//                    it?.previewText ?: "",
//                    it?.previewText ?: "")
//        }
//    }

    fun toDatabase(newsResponseDTO: NewsResponseDTO?, context: Context): List<NewsEntity>? {
        return newsResponseDTO?.results?.mapNotNull { it ->
                NewsEntity(
                        title = it?.title ?: "",
                        imageUrl = if(it?.multimedia?.isNotEmpty() == true) it.multimedia[0]?.url ?: "" else "",
                        newsUrl = it?.url ?: "",
                        newsCategory = it?.section ?: "",
                        publishDate = DateTimeUtils.formatDateForNews(it?.publishDate, context),
                        previewText = it?.previewText ?: "",
                        fullText = it?.previewText ?: "")
            }

        }
    }

