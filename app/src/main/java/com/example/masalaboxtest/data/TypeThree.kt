package com.example.masalaboxtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_three")
data class TypeThree(
    @PrimaryKey(autoGenerate = true)
    val threeId: Long = 0L,
    val name: String
)
