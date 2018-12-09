package com.dmity.androidacademy.features.newsList.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class NewsEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val title: String,
        val imageUrl: String,
        val newsUrl: String,
        val newsCategory: String,
        val publishDate: String,
        val fullText: String
): DisplayableItem, Serializable