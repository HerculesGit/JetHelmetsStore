package com.herco.jethelmetsstore.data.mappers

import com.herco.jethelmetsstore.data.dto.HelmetDto
import com.herco.jethelmetsstore.data.local.HelmetEntity
import com.herco.jethelmetsstore.data.local.HelmetWithSizeEntity
import com.herco.jethelmetsstore.data.local.SizeEntity
import com.herco.jethelmetsstore.domain.model.Helmet
import java.util.UUID


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

fun Helmet.toDtoData(): HelmetDto {
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

fun Helmet.toEntityData(): HelmetWithSizeEntity {

    val helmetId: UUID = UUID.nameUUIDFromBytes(id.toByteArray())
    val helmetEntity = HelmetEntity(
        helmetId = helmetId,
        name,
        brand,
        price,
        favorite,
        details,
    )

    val sizesEntity =
        sizes.map { SizeEntity(sizeId = UUID.randomUUID(), helmetOwnerId = helmetId, size = it) }
            .toList()

    return HelmetWithSizeEntity(helmetEntity, sizesEntity)
}

fun HelmetWithSizeEntity.toDomain(): Helmet {
    return Helmet(
        id = helmet.helmetId.toString(),
        name = helmet.name,
        brand = helmet.brand,
        price = helmet.price,
        favorite = helmet.isFavorite,
        details = helmet.details,
        sizes = sizes.map { it.size } ?: emptyList()
    )
}