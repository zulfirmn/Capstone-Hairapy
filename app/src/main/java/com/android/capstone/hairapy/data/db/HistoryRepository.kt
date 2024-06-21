package com.android.capstone.hairapy.data.db

import android.app.Application

class HistoryRepository(application: Application) {
    private val historyDao: HistoryDao = HistoryRoomDatabase.getDatabase(application).historyDao()

    suspend fun addHistory(history: HistoryPrediction) {
        historyDao.addHistory(history)
    }

    fun getHistory(): List<HistoryPrediction> = historyDao.loadHistory()
}