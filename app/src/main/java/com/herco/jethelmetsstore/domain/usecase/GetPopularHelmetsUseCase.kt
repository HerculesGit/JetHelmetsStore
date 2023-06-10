package com.herco.jethelmetsstore.domain.usecase

import com.herco.jethelmetsstore.domain.mappers.toPresentation
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository
import com.herco.jethelmetsstore.domain.result.Resource
import com.herco.jethelmetsstore.presentation.model.Product

class GetPopularHelmetsUseCase(private val helmetRepository: IHelmetRepository) {
    suspend fun execute(): Resource<List<Product>> {
        val result = helmetRepository.getPopularProducts()
        return when (result) {
            is Resource.Success -> {
                Resource.Success(data = result.data.map { it.toPresentation() }.toList())
            }

            is Resource.Error -> {
                Resource.Error(message = result.message)
            }
        }
    }
}