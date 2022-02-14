package com.example.masalaboxtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.masalaboxtest.data.TypeOne
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeOneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(one: TypeOne)

    @Query("SELECT * FROM type_one ORDER BY oneId DESC")
    suspend fun getTypeOne(): List<TypeOne>
}