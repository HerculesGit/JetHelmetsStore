package com.herco.jethelmetsstore.domain.usecase

import com.herco.jethelmetsstore.domain.mappers.toDomain
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository
import com.herco.jethelmetsstore.domain.result.Resource
import com.herco.jethelmetsstore.presentation.model.Product

class UpdateProductUseCase(
    private val helmetRepository: IHelmetRepository
) {
    suspend fun execute(product: Product): Resource<Unit> {
        return when (val result = helmetRepository.updateProduct(product = product.toDomain())) {
            is Resource.Success -> {
                Resource.Success(data = Unit)
            }

            is Resource.Error -> {
                Resource.Error(message = result.message)
            }
        }
    }
}