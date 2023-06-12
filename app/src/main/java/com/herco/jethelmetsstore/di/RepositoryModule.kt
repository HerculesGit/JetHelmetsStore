package com.herco.jethelmetsstore.di

import com.herco.jethelmetsstore.data.repository.HelmetRepository
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository

class RepositoryModule {
    fun provideHelmetRepository(): IHelmetRepository =
        HelmetRepository(AppModule().provideHelmetApi())
}