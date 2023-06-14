package com.herco.jethelmetsstore.di

import android.content.Context
import com.herco.jethelmetsstore.data.repository.HelmetRepository
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository

class RepositoryModule {
    fun provideHelmetRepository(context: Context): IHelmetRepository =
        HelmetRepository(
            AppModule().provideHelmetApi(),
            AppModule().provideLocalDatabase(context),
            AppModule().provideNetworkUtil(context)
        )
}