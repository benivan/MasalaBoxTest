package com.example.masalaboxtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.masalaboxtest.data.MediumImage

@Dao
interface MediumImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedium(mediumImage: MediumImage)

    @Query("SELECT * FROM medium_image_table ORDER BY secondImageId DESC")
    suspend fun getMediums(): List<MediumImage>
}