package com.dmity.androidacademy

import java.util.*

class NewsItem(val title: String,
               val imageUrl: String,
               val category: Category,
               val publishDate: Date,
               val previewText: String,
               val fullText: String)

class Category(val id: Int,
               val name: String)