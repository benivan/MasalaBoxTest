package com.example.masalaboxtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.masalaboxtest.data.TypeOne
import com.example.masalaboxtest.data.TypeThree
import com.example.masalaboxtest.data.TypeTwo
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeThreeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThree(three: TypeThree)

    @Query("SELECT * FROM type_three ORDER BY threeId DESC")
    suspend fun getTypeThree(): List<TypeThree>
}