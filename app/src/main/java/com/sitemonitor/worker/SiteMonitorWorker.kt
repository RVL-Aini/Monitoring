package com.sitemonitor.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.sitemonitor.service.SiteAvailabilityService
import com.sitemonitor.util.NotificationUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class SiteMonitorWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val siteUrl = inputData.getString("site_url") ?: return@withContext Result.failure()

        val retrofit = Retrofit.Builder()
            .baseUrl(siteUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SiteAvailabilityService::class.java)

        try {
            val response = service.checkAvailability().execute()
            if (response.isSuccessful) {
                Result.success()
            } else {
                NotificationUtil.sendNotification(
                    context,
                    "Сайт $siteUrl недоступен! Код ошибки: ${response.code()}"
                )
                Result.failure()
            }
        } catch (e: IOException) {
            NotificationUtil.sendNotification(
                context,
                "Сайт $siteUrl недоступен! Ошибка: ${e.localizedMessage}"
            )
            Result.failure()
        }
    }
}
