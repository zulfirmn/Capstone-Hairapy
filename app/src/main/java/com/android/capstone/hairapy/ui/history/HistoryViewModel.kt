package com.android.capstone.hairapy.ui.history

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.capstone.hairapy.data.db.HistoryPrediction
import com.android.capstone.hairapy.data.db.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): ViewModel() {
    private val repository: HistoryRepository = HistoryRepository(application)

    var historyList: MutableLiveData<List<HistoryPrediction>> = MutableLiveData()

    init {
        historyList.value = getHistory()
    }

   fun getHistory(): List<HistoryPrediction> = repository.getHistory()

    fun addHistory(history: HistoryPrediction) {
        viewModelScope.launch {
            repository.addHistory(history)
        }
    }
}