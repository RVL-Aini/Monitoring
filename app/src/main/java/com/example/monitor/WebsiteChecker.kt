package com.example.monitor.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

object WebsiteChecker {
    suspend fun checkWebsiteAvailability(context: Context, website: Website) {
        withContext(Dispatchers.IO) {
            try {
                val url = URL(website.url)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.connect()
                if (connection.responseCode != 200) {
                    showNotification(context, "Сайт ${website.url} недоступен!")
                }
            } catch (e: Exception) {
                showNotification(context, "Ошибка: ${website.url} недоступен!")
            }
        }
    }

    private fun showNotification(context: Context, message: String) {
        val channelId = "WebsiteMonitor"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Website Monitor", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Мониторинг сайта")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .build()

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
