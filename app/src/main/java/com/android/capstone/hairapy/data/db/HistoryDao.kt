package com.android.capstone.hairapy.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    suspend fun addHistory(history: HistoryPrediction)

    @Query("SELECT * FROM history_prediction ORDER BY id DESC")
    fun loadHistory(): List<HistoryPrediction>
}