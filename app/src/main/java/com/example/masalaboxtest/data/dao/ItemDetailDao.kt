package com.example.masalaboxtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.masalaboxtest.data.ItemDetail
import com.example.masalaboxtest.data.SmallImage

@Dao
interface ItemDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemDetail(smallImage: ItemDetail)

    @Query("SELECT * FROM item_table ORDER BY itemDetailId DESC")
    suspend fun getItemDetails(): List<ItemDetail>
}