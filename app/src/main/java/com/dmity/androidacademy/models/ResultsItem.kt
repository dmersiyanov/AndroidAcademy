package com.dmity.androidacademy.models

import com.google.gson.annotations.SerializedName

data class ResultsItem(

	@field:SerializedName("subsection")
	val subsection: String? = null,

	@field:SerializedName("section")
	val section: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("multimedia")
	val multimedia: List<MultimediaItem?>? = null,

	@field:SerializedName("published_date")
	val publishedDate: String? = null

)