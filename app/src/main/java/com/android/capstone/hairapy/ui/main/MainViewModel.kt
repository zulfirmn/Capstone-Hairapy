package com.android.capstone.hairapy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.android.capstone.hairapy.data.UserRepository
import com.android.capstone.hairapy.data.api.response.ArticleItem
import com.android.capstone.hairapy.data.api.response.ErrorResponse
import com.android.capstone.hairapy.data.pref.Token
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    private val _listArticles = MutableLiveData<List<ArticleItem>>()
    val listArticles: LiveData<List<ArticleItem>> = _listArticles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    fun getSession(): LiveData<Token> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun articles() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _listArticles.value = repository.articles()
                _isLoading.value = false
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message.toString()
                _message.value = errorMessage
                _isLoading.value = false
            }
        }
    }
}