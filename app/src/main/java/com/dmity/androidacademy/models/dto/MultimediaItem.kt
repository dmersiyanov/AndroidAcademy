package com.dmity.androidacademy.models.dto

import com.google.gson.annotations.SerializedName

data class MultimediaItem(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("subtype")
	val subtype: String? = null,

	@field:SerializedName("format")
	val format: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("caption")
	val caption: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)