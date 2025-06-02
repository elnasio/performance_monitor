package com.mories.performance_monitor.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.URL
import kotlin.system.measureTimeMillis

object SpeedTestClient {

    data class Result(
        val bytesDownloaded: Long,
        val durationMillis: Long,
        val speedMbps: Double
    )

    private const val TEST_FILE_URL = "https://speed.hetzner.de/10MB.bin"

    suspend fun runSpeedTest(): Result = withContext(Dispatchers.IO) {
        var totalBytesRead = 0L
        val buffer = ByteArray(1024 * 4)

        val timeTaken = measureTimeMillis {
            try {
                val connection = URL(TEST_FILE_URL).openConnection()
                connection.connectTimeout = 5000
                connection.getInputStream().use { stream ->
                    BufferedInputStream(stream).use { bis ->
                        while (true) {
                            val read = bis.read(buffer)
                            if (read == -1) break
                            totalBytesRead += read
                            if (totalBytesRead >= 5 * 1024 * 1024) break // limit for speed
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val speedMbps = (totalBytesRead * 8.0) / (timeTaken / 1000.0) / 1_000_000
        Result(
            bytesDownloaded = totalBytesRead,
            durationMillis = timeTaken,
            speedMbps = speedMbps
        )
    }
}