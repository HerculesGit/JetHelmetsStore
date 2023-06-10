package com.herco.jethelmetsstore.domain.repository

import com.herco.jethelmetsstore.domain.model.Helmet
import com.herco.jethelmetsstore.domain.result.Resource

interface IHelmetRepository {
    suspend fun getProduct(id: String): Resource<Helmet>

    suspend fun updateProduct(product: Helmet): Resource<Helmet>

    suspend fun getPopularProducts(): Resource<List<Helmet>>
}