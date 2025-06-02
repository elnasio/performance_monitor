package com.mories.performance_monitor.data.model

data class SpeedTestResult(
    val downloadSpeedMbps: Double,
    val bytesDownloaded: Long,
    val timeTakenMillis: Long
)