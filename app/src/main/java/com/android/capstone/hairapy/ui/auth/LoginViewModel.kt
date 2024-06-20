package com.android.capstone.hairapy.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.capstone.hairapy.data.UserRepository
import com.android.capstone.hairapy.data.api.response.ErrorResponse
import com.android.capstone.hairapy.data.pref.Token
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun userLogin(username: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.userLogin(username, password)
                val accessToken = response.data?.accessToken.toString()
                val refreshToken = response.data?.refreshToken.toString()
                saveSession(Token(accessToken, refreshToken))
                _isLoading.value = false
                _isSuccess.value = true
                _message.value = response.message.toString()
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message.toString()
                _isLoading.value = false
                _isSuccess.value = false
                _message.value = errorMessage
            }
        }
    }

    private fun saveSession(token: Token) {
        viewModelScope.launch {
            repository.saveSession(token)
        }
    }
}