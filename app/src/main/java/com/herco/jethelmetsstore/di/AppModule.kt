package com.herco.jethelmetsstore.di

import com.herco.jethelmetsstore.data.remote.HelmetApi
import com.herco.jethelmetsstore.data.remote.RetrofitClient

class AppModule {
    fun provideHelmetApi(): HelmetApi =
        RetrofitClient().buildHelmetApi()
}