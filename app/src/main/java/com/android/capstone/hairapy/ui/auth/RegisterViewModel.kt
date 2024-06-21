package com.android.capstone.hairapy.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.capstone.hairapy.data.UserRepository
import com.android.capstone.hairapy.data.api.response.ErrorResponse
import com.android.capstone.hairapy.data.utils.SingleLiveEvent
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _message = SingleLiveEvent<String>()
    val message: LiveData<String> = _message

    fun userSignUp(username: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.userSignUp(username, password)
                _isLoading.value = false
                _isSuccess.value = true
                _message.value = "Registration successful, now you can login!"
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
}