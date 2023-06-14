package com.herco.jethelmetsstore.di

import android.content.Context
import com.herco.jethelmetsstore.domain.usecase.GetPopularHelmetsUseCase
import com.herco.jethelmetsstore.domain.usecase.GetProductUseCase
import com.herco.jethelmetsstore.domain.usecase.UpdateProductUseCase

class UseCaseModule(private val context: Context) {

    private val repositoryModule = RepositoryModule()
    private val helmetRepository = repositoryModule.provideHelmetRepository(context)

    fun provideGetPopularHelmetsUseCase() = GetPopularHelmetsUseCase(helmetRepository)

    fun provideGetProductUseCase() = GetProductUseCase(helmetRepository)

    fun provideUpdateProductUseCase() = UpdateProductUseCase(helmetRepository)

}