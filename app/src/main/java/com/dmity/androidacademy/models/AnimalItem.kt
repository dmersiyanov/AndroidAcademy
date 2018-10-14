package com.dmity.androidacademy.models

import java.util.*

class AnimalItem(val title: String = "",
                 val imageUrl: String = "",
                 val newsCategory: Categories? = null,
                 val publishDate: Date = Date(),
                 val previewText: String = "",
                 val fullText: String = ""): DisplayableItem {
}