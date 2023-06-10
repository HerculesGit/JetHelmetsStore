package com.herco.jethelmetsstore.data.mappers

import com.herco.jethelmetsstore.data.dto.HelmetDto
import com.herco.jethelmetsstore.domain.model.Helmet


fun HelmetDto.toDomain(): Helmet {
    return Helmet(
        id = id,
        name = name,
        brand = brand,
        price = price,
        favorite = isFavorite,
        details = details,
        sizes = sizes
    )
}

fun Helmet.toData(): HelmetDto {
    return HelmetDto(
        id = id,
        name = name,
        brand = brand,
        price = price,
        isFavorite = favorite,
        details = details,
        sizes = sizes
    )
}
