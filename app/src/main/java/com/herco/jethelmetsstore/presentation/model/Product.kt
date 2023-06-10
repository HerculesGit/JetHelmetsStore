package com.herco.jethelmetsstore.presentation.model

data class Product(
    val id: String,
    val brand: String = "jet helmet",
    val name: String = "Caberg Riveira",
    val price: Double = 65.25,
    val favorite: Boolean = false,
    val details: String = "RIVIERA V3 has been realized in three shell sizes in order to offer the best fitting proportion between the shell volume and the motorcyclist's head..",
    val sizes: List<String> = listOf()
)
