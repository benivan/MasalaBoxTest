package com.example.masalaboxtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_two")
data class TypeTwo(
    @PrimaryKey(autoGenerate = true)
    val twoId: Long = 0L,
    val name: String
)
