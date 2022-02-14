package com.example.masalaboxtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class ItemDetail(
    @PrimaryKey(autoGenerate = true)
    val itemDetailId: Long = 0L,
    val name: String,
    val smallImage: String?,
    val middleImage: String?,
    val textOne: String,
    val textTwo: String,
)
