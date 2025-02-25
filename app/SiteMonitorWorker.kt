import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException

class SiteMonitorWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

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
                sendNotification("Site $siteUrl is down!")
                Result.failure()
            }
        } catch (e: IOException) {
            sendNotification("Site $siteUrl is down!")
            Result.failure()
        }
    }

    private fun sendNotification(message: String) {
        // Здесь код для отправки уведомления
        // Используйте NotificationManager для создания и отправки уведомления
    }
}

interface SiteAvailabilityService {
    @GET("/")
    fun checkAvailability(): retrofit2.Call<Void>
}
