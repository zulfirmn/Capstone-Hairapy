package com.android.capstone.hairapy.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val imageUrl: String?,
    val title: String?,
    val content: String?
): Parcelable
