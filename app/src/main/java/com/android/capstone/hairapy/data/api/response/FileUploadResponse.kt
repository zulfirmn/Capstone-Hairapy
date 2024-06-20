package com.android.capstone.hairapy.data.api.response

import com.google.gson.annotations.SerializedName

data class FileUploadResponse(

	@field:SerializedName("data")
	val data: Result? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class RecomendationsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Result(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("ingredients")
	val ingredients: List<String?>? = null,

	@field:SerializedName("recomendations")
	val recomendations: List<RecomendationsItem?>? = null
)
