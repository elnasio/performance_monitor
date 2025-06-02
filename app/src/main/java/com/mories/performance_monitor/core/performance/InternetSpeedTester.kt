package com.mories.performance_monitor.core.performance

import android.util.Log
import com.mories.performance_monitor.data.model.SpeedTestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.math.max
import kotlin.system.measureTimeMillis

object InternetSpeedMonitor {
    private const val TEST_FILE_URL = "https://download.samplelib.com/mp4/sample-5s.mp4"

    fun observeSpeed(intervalMillis: Long = 5000): Flow<SpeedTestResult> = flow {
        while (true) {
            val result = testDownloadSpeed()
            emit(result)
            delay(intervalMillis)
        }
    }

    private suspend fun testDownloadSpeed(): SpeedTestResult = withContext(Dispatchers.IO) {
        val buffer = ByteArray(1024 * 4)
        var bytesRead = 0L

        val timeTaken = measureTimeMillis {
            try {
                val connection = URL(TEST_FILE_URL).openConnection().apply {
                    connectTimeout = 5000
                    readTimeout = 10000
                }

                connection.getInputStream().use { input ->
                    while (true) {
                        val read = input.read(buffer)
                        if (read == -1) {
                            Log.d("SpeedTest", "End of stream reached.")
                            break
                        }
                        bytesRead += read
                        Log.d("SpeedTest", "Read $read bytes, total downloaded: $bytesRead bytes")

                        if (bytesRead >= 5 * 1024 * 1024) {
                            Log.d("SpeedTest", "5MB limit reached, stopping download early.")
                            break
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("SpeedTest", "Download failed: ${e.message}", e)
            }
        }

        val safeTime = max(timeTaken, 1)
        val speedMbps = (bytesRead * 8.0) / (safeTime / 1000.0) / 1_000_000

        Log.d("SpeedTest", "Download complete.")
        Log.d("SpeedTest", "Bytes downloaded: $bytesRead")
        Log.d("SpeedTest", "Time taken: $timeTaken ms")
        Log.d("SpeedTest", "Speed: %.2f Mbps".format(speedMbps))

        SpeedTestResult(
            downloadSpeedMbps = speedMbps, bytesDownloaded = bytesRead, timeTakenMillis = timeTaken
        )
    }
}