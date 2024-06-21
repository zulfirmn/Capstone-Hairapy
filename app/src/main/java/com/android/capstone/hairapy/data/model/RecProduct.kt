package com.android.capstone.hairapy.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecProduct(
    val image: String?,
    val name: String?
): Parcelable
