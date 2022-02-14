package com.example.masalaboxtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.masalaboxtest.data.TypeOne
import com.example.masalaboxtest.data.TypeTwo
import kotlinx.coroutines.flow.Flow
import java.nio.channels.FileLock

@Dao
interface TypeTwoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTwo(typeTwo: TypeTwo)

    @Query("SELECT * FROM type_two ORDER BY twoId DESC")
    suspend fun getTypeTwo(): List<TypeTwo>
}