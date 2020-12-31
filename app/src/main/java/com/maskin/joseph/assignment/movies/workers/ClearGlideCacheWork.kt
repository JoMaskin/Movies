package com.maskin.joseph.assignment.movies.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClearGlideCacheWork(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            Glide.get(applicationContext).clearDiskCache()
            Log.i(TAG, "Glide cache cleared successfully")
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }
    }

    companion object {
        const val TAG = "ClearGlideCacheWork "
    }
}