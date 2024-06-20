package com.android.capstone.hairapy.di

import android.content.Context
import com.android.capstone.hairapy.data.UserRepository
import com.android.capstone.hairapy.data.api.retrofit.ApiConfig
import com.android.capstone.hairapy.data.pref.UserPreference
import com.android.capstone.hairapy.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService(context)
        return UserRepository.getInstance(pref, apiService)
    }
}