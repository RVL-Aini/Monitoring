package com.sitemonitor.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.sitemonitor.worker.SiteMonitorWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupWorkManager()
    }

    private fun setupWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val siteUrl = "https://example.com" // В реальном приложении должно браться из настроек

        val data = Data.Builder()
            .putString("site_url", siteUrl)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(
            SiteMonitorWorker::class.java,
            15, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}
