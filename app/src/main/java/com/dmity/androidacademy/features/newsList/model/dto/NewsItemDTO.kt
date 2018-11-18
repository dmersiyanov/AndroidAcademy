package com.dmity.androidacademy.features.newsList.model.dto

import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsItemDTO(

        @field:SerializedName("subsection")
        val subsection: String? = null,

        @field:SerializedName("section")
        val section: String? = null,

        @field:SerializedName("abstract")
        val previewText: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("url")
        val url: String? = null,

        @field:SerializedName("multimedia")
        val multimedia: List<MultimediaItemDTO?>? = null,

        @field:SerializedName("published_date")
        val publishDate: Date? = null

): DisplayableItem