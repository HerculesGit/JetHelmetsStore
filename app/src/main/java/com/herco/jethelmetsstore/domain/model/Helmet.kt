package com.herco.jethelmetsstore.domain.model


data class Helmet(
    val id: String,
    val brand: String,
    val name: String,
    val price: Double,
    val favorite: Boolean,
    val details: String,
    val sizes: List<String>
)

