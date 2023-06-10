package com.herco.jethelmetsstore.domain.mappers

import com.herco.jethelmetsstore.domain.model.Helmet
import com.herco.jethelmetsstore.presentation.model.Product

fun Helmet.toPresentation(): Product {
    return Product(
        id = id,
        name = name,
        brand = brand,
        price = price,
        favorite = favorite,
        details = details,
        sizes = sizes
    )
}

fun Product.toDomain(): Helmet {
    return Helmet(
        id = id,
        name = name,
        brand = brand,
        price = price,
        favorite = favorite,
        details = details,
        sizes = sizes
    )
}
