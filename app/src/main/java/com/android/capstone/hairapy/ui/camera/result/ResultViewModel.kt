package com.android.capstone.hairapy.ui.camera.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.capstone.hairapy.data.UserRepository
import com.android.capstone.hairapy.data.api.response.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class ResultViewModel(private val repository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    fun uploadImage(image: File) {
        val requestFile = image.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", image.name, requestFile)
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.uploadImg(body)
                _isLoading.value = false
                _result.value = response.data?.result.toString()
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message.toString()
                _isLoading.value = false
                _result.value = errorMessage
            }
        }
    }
}