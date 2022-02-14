package com.example.masalaboxtest.di

import android.content.Context
import com.example.masalaboxtest.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Database {

    @Provides
    fun providesDaoOne(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context).typeOneDao()

    @Provides
    fun providesDaoTwo(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context).typeTwoDao()

    @Provides
    fun providesTypeThreeDao(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context).typeThreeDao()


    @Provides
    fun provideSmallImageDao(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context).smallImageDao()

    @Provides
    fun provideMediumImageDao(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context).mediumImageDao()

    @Provides
    fun provideItemDetailDao(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context).itemDetailDao()
}