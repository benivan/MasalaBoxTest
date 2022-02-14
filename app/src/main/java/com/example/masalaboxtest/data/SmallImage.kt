package com.example.masalaboxtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "small_image_table")
data class SmallImage(
    @PrimaryKey(autoGenerate = true)
    val smallImageId: Long = 0L,
    val name: String,
    val smallImage: String?
)
