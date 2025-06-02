package com.mories.performance_monitor.data.repository

import android.content.Context
import com.mories.performance_monitor.core.performance.CpuMonitor
import com.mories.performance_monitor.core.performance.FpsMonitor
import com.mories.performance_monitor.core.performance.RamMonitor
import com.mories.performance_monitor.core.performance.SystemInfoUtil
import com.mories.performance_monitor.data.model.CpuInfo
import com.mories.performance_monitor.data.model.FpsInfo
import com.mories.performance_monitor.data.model.RamInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PerformanceRepository(private val context: Context) {

    private var fpsMonitor: FpsMonitor? = null

    suspend fun getCpuInfo(): CpuInfo = withContext(Dispatchers.Default) {
        CpuInfo(
            usagePercent = CpuMonitor.getCpuUsage(),
            coreCount = Runtime.getRuntime().availableProcessors(),
            architecture = SystemInfoUtil.getCpuArchitecture(),
            deviceName = SystemInfoUtil.getDeviceName(),
        )
    }

    fun getRamInfo(): RamInfo {
        val ram = RamMonitor.getRamUsage(context)
        return RamInfo(
            totalMemBytes = ram.totalMem,
            usedMemBytes = ram.usedMem,
            availMemBytes = ram.availMem,
            usagePercent = ram.usagePercent
        )
    }

    fun startFpsMonitor(onFpsUpdate: (FpsInfo) -> Unit) {
        fpsMonitor = FpsMonitor { fps ->
            onFpsUpdate(FpsInfo(fps))
        }
        fpsMonitor?.start()
    }

    fun stopFpsMonitor() {
        fpsMonitor?.stop()
    }
}