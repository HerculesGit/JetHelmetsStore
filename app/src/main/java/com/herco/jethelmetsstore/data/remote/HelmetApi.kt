package com.herco.jethelmetsstore.data.remote

import com.herco.jethelmetsstore.data.dto.HelmetDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HelmetApi {
    @GET("products/popular")
    suspend fun getPopularProducts(): List<HelmetDto>

    @GET("products/{id}")
    suspend fun getProductData(@Path("id") id: String): HelmetDto
}