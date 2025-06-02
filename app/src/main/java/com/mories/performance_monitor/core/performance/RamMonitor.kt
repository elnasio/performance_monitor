package com.mories.performance_monitor.core.performance

import android.app.ActivityManager
import android.content.Context
import android.os.Build

object RamMonitor {

    data class RamUsage(
        val totalMem: Long,
        val availMem: Long,
        val usedMem: Long,
        val usagePercent: Float
    )

    fun getRamUsage(context: Context): RamUsage {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memInfo)

        val totalMem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            memInfo.totalMem
        } else {
            Runtime.getRuntime().totalMemory()
        }

        val availMem = memInfo.availMem
        val usedMem = totalMem - availMem
        val usagePercent = usedMem.toFloat() / totalMem * 100f

        return RamUsage(
            totalMem = totalMem,
            availMem = availMem,
            usedMem = usedMem,
            usagePercent = usagePercent
        )
    }
}