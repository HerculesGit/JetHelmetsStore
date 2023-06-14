package com.herco.jethelmetsstore.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID


const val helmetTableName = "helmet"


@Entity(tableName = helmetTableName)
data class HelmetEntity(
    @PrimaryKey val helmetId: UUID,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean,
    @ColumnInfo(name = "details") val details: String,
)

const val helmetSizeTableName = "helmetSize"

@Entity(tableName = helmetSizeTableName)
data class SizeEntity(
    @PrimaryKey val sizeId: UUID,
    @ColumnInfo(name = "helmetOwnerId") val helmetOwnerId: UUID,
    @ColumnInfo(name = "size") val size: String,
)

//@Entity(tableName = "helmet_size_cross_ref", primaryKeys = ["helmetId", "helmetOwnerId"])
data class HelmetWithSizeEntity(
    @Embedded val helmet: HelmetEntity,

    @Relation(
        parentColumn = "helmetId",// from HelmetEntity
        entityColumn = "helmetOwnerId" // to SizeEntity
    )
    val sizes: List<SizeEntity>
)