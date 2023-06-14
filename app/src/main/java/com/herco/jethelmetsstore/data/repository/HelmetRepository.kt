package com.herco.jethelmetsstore.data.repository

import com.herco.jethelmetsstore.data.local.AppDatabase
import com.herco.jethelmetsstore.data.mappers.toDomain
import com.herco.jethelmetsstore.data.mappers.toEntityData
import com.herco.jethelmetsstore.data.remote.HelmetApi
import com.herco.jethelmetsstore.domain.model.Helmet
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository
import com.herco.jethelmetsstore.domain.result.Resource
import com.herco.jethelmetsstore.util.NetworkUtils
import java.util.UUID

class HelmetRepository(
    private val api: HelmetApi,
    private val localDatabase: AppDatabase,
    private val networkUtils: NetworkUtils
) : IHelmetRepository {
    override suspend fun getProduct(id: String): Resource<Helmet> {
        if (networkUtils.isNetworkConnected()) {
            return try {
                val response = api.getProductData(id)

                savePopularHelmetsOnLocal(listOf(response.toDomain()))

                Resource.Success(data = response.toDomain())
            } catch (e: Exception) {
                Resource.Error("Failed to fetch product with id={$id}. \nException { \nmsg=${e.message},\ncause={${e.cause}} }")
            }
        }

        return try {
            Resource.Success(
                localDatabase.helmetDao().getHelmetWithSizeById(UUID.fromString(id)).toDomain()
            )
        } catch (e: Exception) {
            Resource.Error(message = "error ${e.cause}")
        }
    }

    override suspend fun updateProduct(product: Helmet): Resource<Helmet> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularProducts(): Resource<List<Helmet>> {
        if (networkUtils.isNetworkConnected()) {
            return try {
                val response = api.getPopularProducts()
                val helmetList = response.map { helmetDto -> helmetDto.toDomain() }.toList()

                savePopularHelmetsOnLocal(helmetList)

                Resource.Success(data = helmetList)
            } catch (e: Exception) {
                Resource.Error("Failed to fetch popular products\ncause=${e.cause}")
            }
        }

        return try {
            Resource.Success(
                data = localDatabase.helmetDao().getHelmetsWithSizes().map { it.toDomain() })
        } catch (e: Exception) {
            Resource.Error(message = "error ${e.cause}")
        }

    }

    private suspend fun savePopularHelmetsOnLocal(helmetList: List<Helmet>) {
        helmetList.forEach {
            val helmetWithEntity = it.toEntityData()
            val helmetEntity = helmetWithEntity.helmet
            val sizes = helmetWithEntity.sizes

            localDatabase.helmetDao().insertHelmet(helmetEntity)
            localDatabase.helmetDao().deleteAllSizesFromHelmetId(helmetEntity.helmetId)
            sizes.forEach { size -> localDatabase.helmetDao().insertSize(size) }
            print(sizes)
        }
    }
}