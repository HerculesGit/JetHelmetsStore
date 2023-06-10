package com.herco.jethelmetsstore.data.dto

data class HelmetDto(
    val id: String,
    val brand: String,
    val name: String,
    val price: Double,
    val isFavorite: Boolean,
    val details: String,
    val sizes: List<String>
)
