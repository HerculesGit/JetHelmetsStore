package com.herco.jethelmetsstore.di

import android.content.Context
import com.herco.jethelmetsstore.data.local.AppDatabase
import com.herco.jethelmetsstore.data.remote.HelmetApi
import com.herco.jethelmetsstore.data.remote.RetrofitClient
import com.herco.jethelmetsstore.util.NetworkUtils

class AppModule {
    fun provideHelmetApi(): HelmetApi =
        RetrofitClient().buildHelmetApi()

    fun provideLocalDatabase(context: Context): AppDatabase {
        return LocalDatabaseModule.getDatabase(context)
    }

    fun provideNetworkUtil(context: Context): NetworkUtils = NetworkUtils(context)
}