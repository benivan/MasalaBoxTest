package com.example.masalaboxtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medium_image_table")
data class MediumImage(
    @PrimaryKey(autoGenerate = true)
    val secondImageId: Long = 0L,
    val name: String,
    val middleImage: String?,
)
