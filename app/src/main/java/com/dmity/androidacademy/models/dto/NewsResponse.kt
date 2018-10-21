package com.dmity.androidacademy.models.dto

import com.google.gson.annotations.SerializedName

data class NewsResponse(

		@field:SerializedName("section")
	val section: String? = null,

		@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

		@field:SerializedName("num_results")
	val numResults: Int? = null,

		@field:SerializedName("status")
	val status: String? = null
)