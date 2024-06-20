package com.android.capstone.hairapy.data.api.retrofit

import com.android.capstone.hairapy.data.api.response.ArticlesResponse
import com.android.capstone.hairapy.data.api.response.AuthResponse
import com.android.capstone.hairapy.data.api.response.FileUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("api/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String
    ): AuthResponse

    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): AuthResponse

    @FormUrlEncoded
    @POST("api/token")
    suspend fun token(
        @Field("refreshToken") token: String
    ): AuthResponse

    @GET("api/articles")
    suspend fun getArticles(): ArticlesResponse

    @Multipart
    @POST("api/predict")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): FileUploadResponse

    @FormUrlEncoded
    @DELETE("api/logout")
    suspend fun logout(
        @Field("refreshToken") token: String
    ): AuthResponse
}