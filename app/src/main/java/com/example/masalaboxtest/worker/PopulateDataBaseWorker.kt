package com.example.masalaboxtest.worker

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.masalaboxtest.data.*
import com.example.masalaboxtest.util.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopulateDataBaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
        try {
            val sharedPreferences =
                applicationContext.getSharedPreferences("doctor", Context.MODE_PRIVATE)
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val dataType = object : TypeToken<JsonNameData>() {}.type
                        val dataList: JsonNameData = Gson().fromJson(jsonReader, dataType)


                        val database = AppDatabase.getInstance(applicationContext)
                        dataList.page1.forEach { data ->
                            when (data.type) {
                                "One" -> database.typeOneDao().insertOne(TypeOne(name = data.name))
                                "Two" -> database.typeTwoDao().insertTwo(TypeTwo(name = data.name))
                                "Three" -> database.typeThreeDao().insertThree(TypeThree(name = data.name))
                            }
                        }
                        dataList.page2up.forEach { data ->
                            database.smallImageDao().insertSmallImage(
                                SmallImage(
                                name = data.name,
                                smallImage = data.smallImage
                            )
                            )
                        }

                        dataList.page2middle.forEach { data ->
                            database.mediumImageDao().insertMedium(
                                MediumImage(
                                name = data.name,
                                middleImage = data.middleImage
                            )
                            )
                        }

                        dataList.page2down.forEach { data ->
                            database.itemDetailDao().insertItemDetail(ItemDetail(
                                name = data.name,
                                smallImage = data.smallImage,
                                middleImage = data.middleImage,
                                textOne = data.textOne,
                                textTwo = data.textTwo
                            ))
                        }


                        sharedPreferences.edit {
                            putBoolean("databaseSeeded", true)
                        }

                    }
                }
                Result.success()
            } else {
                Log.e(TAG, "Error populating database -no valid filename")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error populating data", e)
            Result.failure()
        }
    }


    companion object {
        private const val TAG = "PopulateDatabaseWorker"
        const val KEY_FILENAME = "PROBLEM_DATA_FILENAME"
    }
}