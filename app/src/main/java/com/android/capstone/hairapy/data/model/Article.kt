package com.android.capstone.hairapy.data.model

import androidx.annotation.DrawableRes

data class Article (
    val id: String? = null,
    val title:String? = null,
    val content:String? = null,
    @DrawableRes val image:Int
)