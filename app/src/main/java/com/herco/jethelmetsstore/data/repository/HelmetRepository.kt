package com.herco.jethelmetsstore.data.repository

import com.herco.jethelmetsstore.data.mappers.toDomain
import com.herco.jethelmetsstore.data.remote.HelmetApi
import com.herco.jethelmetsstore.domain.model.Helmet
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository
import com.herco.jethelmetsstore.domain.result.Resource

class HelmetRepository(private val api: HelmetApi) : IHelmetRepository {
    override suspend fun getProduct(id: String): Resource<Helmet> {
        return try {
            val response = api.getProductData(id)
            Resource.Success(data = response.toDomain())
        } catch (e: Exception) {
            Resource.Error("Failed to fetch product with id={$id}. \nException { \nmsg=${e.message},\ncause={${e.cause}} }")
        }
    }

    override suspend fun updateProduct(product: Helmet): Resource<Helmet> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularProducts(): Resource<List<Helmet>> {
        return try {
            val response = api.getPopularProducts()
            val helmetList = response.map { helmetDto -> helmetDto.toDomain() }.toList()
            Resource.Success(data = helmetList)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch popular products with")
        }
    }
}