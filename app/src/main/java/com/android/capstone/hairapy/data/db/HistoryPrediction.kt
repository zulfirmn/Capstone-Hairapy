package com.android.capstone.hairapy.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_prediction")
data class HistoryPrediction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "result")
    val result: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "uri")
    val uri:String
)
