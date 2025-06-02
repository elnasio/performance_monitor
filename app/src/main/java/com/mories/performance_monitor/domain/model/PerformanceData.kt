package com.mories.performance_monitor.domain.model

import com.mories.performance_monitor.data.model.CpuInfo
import com.mories.performance_monitor.data.model.RamInfo
import com.mories.performance_monitor.data.model.SpeedTestResult

data class PerformanceData(
    val cpu: CpuInfo,
    val ram: RamInfo,
    val speedTest: SpeedTestResult
)