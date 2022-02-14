package com.example.masalaboxtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.masalaboxtest.data.SmallImage

@Dao
interface SmallImageDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertSmallImage(smallImage: SmallImage)

        @Query("SELECT * FROM small_image_table ORDER BY smallImageId DESC")
        suspend fun getSmallImage(): List<SmallImage>
}