package com.android.capstone.hairapy.data

import com.android.capstone.hairapy.data.api.response.ArticleItem
import com.android.capstone.hairapy.data.api.response.AuthResponse
import com.android.capstone.hairapy.data.api.response.FileUploadResponse
import com.android.capstone.hairapy.data.api.retrofit.ApiService
import com.android.capstone.hairapy.data.db.HistoryPrediction
import com.android.capstone.hairapy.data.pref.Token
import com.android.capstone.hairapy.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun saveSession(token: Token) {
        userPreference.saveSession(token)
    }

    fun getSession(): Flow<Token> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun userSignUp(username: String, password: String): AuthResponse {
        return apiService.register(username, password)
    }

    suspend fun userLogin(username: String, password: String): AuthResponse {
        return apiService.login(username, password)
    }

    suspend fun refreshToken(refreshToken: String): AuthResponse {
        return apiService.token(refreshToken)
    }

    suspend fun articles(): List<ArticleItem> {
        return apiService.getArticles().data.articles
    }

    suspend fun uploadImg(body: MultipartBody.Part): FileUploadResponse {
        return apiService.uploadImage(body)
    }

    suspend fun addHistory(history: HistoryPrediction) {
        history
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}