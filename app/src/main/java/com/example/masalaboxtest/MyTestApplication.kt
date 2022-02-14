package com.example.masalaboxtest

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.masalaboxtest.util.PROBLEM_DATA_FILENAME
import com.example.masalaboxtest.worker.PopulateDataBaseWorker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyTestApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        val sharedPreferences =
            applicationContext.getSharedPreferences("doctor", Context.MODE_PRIVATE)
        val isDatabaseSeeded = sharedPreferences.getBoolean("databaseSeeded", false)
        Log.d(TAG, "onCreate: $isDatabaseSeeded")
        if (!isDatabaseSeeded) {
            val request =
                OneTimeWorkRequestBuilder<PopulateDataBaseWorker>()
                    .addTag("seed-worker")
                    .setInputData(workDataOf(PopulateDataBaseWorker.KEY_FILENAME to PROBLEM_DATA_FILENAME))
                    .build()
            WorkManager.getInstance(this).enqueue(request)
        }
    }
    
    companion object{
        private const val TAG = "MyTestApplication"
    }
}