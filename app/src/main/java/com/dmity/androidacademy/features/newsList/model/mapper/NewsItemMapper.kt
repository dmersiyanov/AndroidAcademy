package com.dmity.androidacademy.features.newsList.model.mapper

import android.content.Context
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.features.newsList.model.NewsItem
import com.dmity.androidacademy.features.newsList.model.dto.NewsResponseDTO
import com.dmity.androidacademy.utils.DateTimeUtils

class NewsItemMapper {

    fun fromDatabase(newsEntities: List<NewsEntity>?): List<NewsItem> {
        val newsItemsList = newsEntities?.map { it ->
            NewsItem(
                    it.title,
                    it.imageUrl,
                    it.newsCategory,
                    it.publishDate,
                    it.fullText)
        }

        return newsItemsList ?: emptyList()
    }

    fun toDatabase(newsResponseDTO: NewsResponseDTO?, context: Context): List<NewsEntity>? {
        return newsResponseDTO?.results?.mapNotNull { it ->
                NewsEntity(
                        title = it?.title ?: "",
                        imageUrl = if(it?.multimedia?.isNotEmpty() == true) it.multimedia[3]?.url ?: "" else "",
                        newsUrl = it?.url ?: "",
                        newsCategory = it?.section ?: "",
                        publishDate = DateTimeUtils.formatDateForNews(it?.publishDate, context),
                        fullText = it?.previewText ?: "")
            }

        }
    }

