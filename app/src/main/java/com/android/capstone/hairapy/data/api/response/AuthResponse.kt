package com.android.capstone.hairapy.data.api.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("userID")
	val userID: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)
