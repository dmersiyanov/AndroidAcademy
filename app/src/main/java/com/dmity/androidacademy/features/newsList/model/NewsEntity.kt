package com.dmity.androidacademy.features.newsList.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class NewsEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val title: String,
        val imageUrl: String,
        val newsUrl: String,
        val newsCategory: Categories,
        val publishDate: Date,
        val previewText: String,
        val fullText: String

)