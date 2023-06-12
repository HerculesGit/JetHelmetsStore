package com.herco.jethelmetsstore.di

import com.herco.jethelmetsstore.domain.usecase.GetPopularHelmetsUseCase
import com.herco.jethelmetsstore.domain.usecase.GetProductUseCase
import com.herco.jethelmetsstore.domain.usecase.UpdateProductUseCase

class UseCaseModule {

    companion object {

        private val repositoryModule = RepositoryModule()
        private val helmetRepository = repositoryModule.provideHelmetRepository();

        fun provideGetPopularHelmetsUseCase() = GetPopularHelmetsUseCase(helmetRepository)

        fun provideGetProductUseCase() = GetProductUseCase(helmetRepository)

        fun provideUpdateProductUseCase() = UpdateProductUseCase(helmetRepository)
    }
}