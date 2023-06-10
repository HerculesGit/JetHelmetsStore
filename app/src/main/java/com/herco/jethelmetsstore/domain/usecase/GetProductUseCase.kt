package com.herco.jethelmetsstore.domain.usecase

import com.herco.jethelmetsstore.domain.mappers.toPresentation
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository
import com.herco.jethelmetsstore.domain.result.Resource
import com.herco.jethelmetsstore.presentation.model.Product

class GetProductUseCase(
    private val helmetRepository: IHelmetRepository
) {
    suspend fun execute(id: String): Resource<Product> {
        val result = helmetRepository.getProduct(id)
        return when (result) {
            is Resource.Success -> {
                Resource.Success(data = result.data.toPresentation())
            }

            is Resource.Error -> {
                Resource.Error(message = result.message)
            }
        }
    }
}