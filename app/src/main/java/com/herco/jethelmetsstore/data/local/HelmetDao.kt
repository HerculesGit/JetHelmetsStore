package com.herco.jethelmetsstore.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import java.util.UUID

@Dao
interface HelmetDao {
//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertHelmetWithSize(helmetEntityWithSizeEntity: HelmetWithSizeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHelmet(helmetEntity: HelmetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSize(sizeEntity: SizeEntity)

    @Query("DELETE FROM $helmetSizeTableName WHERE helmetOwnerId = :helmetId")
    suspend fun deleteAllSizesFromHelmetId(helmetId: UUID)

    @Transaction
    @Query("SELECT * FROM $helmetTableName WHERE helmetId= :helmetId")
    suspend fun getHelmetWithSizeById(helmetId: UUID): HelmetWithSizeEntity

    @Transaction
    @Query("SELECT * FROM $helmetTableName")
    suspend fun getHelmetsWithSizes(): List<HelmetWithSizeEntity>
}