package com.example.masalaboxtest.repository

import com.example.masalaboxtest.data.ItemDetail
import com.example.masalaboxtest.data.MediumImage
import com.example.masalaboxtest.data.SmallImage
import com.example.masalaboxtest.data.dao.*
import com.example.masalaboxtest.util.ItemRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val typeOneDao: TypeOneDao,
    private val tyeTwoDao: TypeTwoDao,
    private val typeThreeDao: TypeThreeDao,
    private val smallImageDao: SmallImageDao,
    private val mediumImageDao: MediumImageDao,
    private val itemDetailDao: ItemDetailDao
) {
    suspend fun getAllNamedData(): List<ItemRow> {
        val typeOne = typeOneDao.getTypeOne()
        val typeTwo = tyeTwoDao.getTypeTwo()
        val typeThree = typeThreeDao.getTypeThree()

        val list = mutableListOf<ItemRow>()

        list.addAll(typeOne.map {
            ItemRow.One(it)
        })

        list.addAll(typeTwo.map {
            ItemRow.Two(it)
        })

        list.addAll(typeThree.map {
            ItemRow.Three(it)
        })

        return list.shuffled()
    }

    suspend fun getTopData(): List<SmallImage>{
        return smallImageDao.getSmallImage()
    }

    suspend fun getMiddleData():List<MediumImage>{
        return mediumImageDao.getMediums()
    }

    suspend fun getItemDetail():List<ItemDetail>{
        return itemDetailDao.getItemDetails()
    }

    companion object {
        private const val TAG = "RoomRepository"
    }
}