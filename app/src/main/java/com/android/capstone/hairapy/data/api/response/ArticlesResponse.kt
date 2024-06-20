package com.android.capstone.hairapy.data.api.response

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("data")
	val data: Articles,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ArticleItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class Articles(

	@field:SerializedName("articles")
	val articles: List<ArticleItem> = emptyList()
)
