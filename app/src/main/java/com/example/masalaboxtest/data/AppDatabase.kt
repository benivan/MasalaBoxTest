package com.example.masalaboxtest.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.masalaboxtest.data.dao.*
import com.example.masalaboxtest.util.DATABASE_NAME


@Database(
    entities = [TypeOne::class, TypeTwo::class, TypeThree::class,SmallImage::class,MediumImage::class,ItemDetail::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun typeOneDao(): TypeOneDao
    abstract fun typeTwoDao(): TypeTwoDao
    abstract fun typeThreeDao(): TypeThreeDao
    abstract fun smallImageDao():SmallImageDao
    abstract fun mediumImageDao():MediumImageDao
    abstract fun itemDetailDao():ItemDetailDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .build().also {
                        INSTANCE = it
                    }
            }
        }

        private const val TAG = "AppDatabase"
    }
}