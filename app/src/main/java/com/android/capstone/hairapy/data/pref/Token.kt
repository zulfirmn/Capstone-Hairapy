package com.android.capstone.hairapy.data.pref

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val isLogin: Boolean = false
)