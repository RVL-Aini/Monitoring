package com.example.your_app_name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val siteUrl = "https://example.com"

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

@Composable
fun MainScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Site Monitor")
        Button(onClick = { /* TODO */ }) {
            Text(text = "Start Monitoring")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
