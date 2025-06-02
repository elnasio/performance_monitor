package com.mories.performance_monitor.core.performance

import java.io.RandomAccessFile

object CpuMonitor {
    fun getCpuUsage(): Float {
        return try {
            val reader = RandomAccessFile("/proc/stat", "r")
            val load1 = reader.readLine().split(" ")

            val idle1 = load1.filter { it.isNotBlank() }[4].toLong()
            val total1 = load1.filter { it.isNotBlank() }.drop(1).take(7).map { it.toLong() }.sum()

            Thread.sleep(360)

            reader.seek(0)
            val load2 = reader.readLine().split(" ")
            reader.close()

            val idle2 = load2.filter { it.isNotBlank() }[4].toLong()
            val total2 = load2.filter { it.isNotBlank() }.drop(1).take(7).map { it.toLong() }.sum()

            val idleDelta = idle2 - idle1
            val totalDelta = total2 - total1

            ((totalDelta - idleDelta).toFloat() / totalDelta) * 100f
        } catch (e: Exception) {
            e.printStackTrace()
            -1f
        }
    }
}