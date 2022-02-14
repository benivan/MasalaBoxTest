package com.example.masalaboxtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.masalaboxtest.util.ItemRow

@Entity(tableName = "type_one")
data class TypeOne(
    @PrimaryKey(autoGenerate = true)
    val oneId:Long = 0L,
    val name: String,
)
