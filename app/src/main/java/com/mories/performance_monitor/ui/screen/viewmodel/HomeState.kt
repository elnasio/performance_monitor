package com.mories.performance_monitor.ui.screen.viewmodel

import com.mories.performance_monitor.data.model.CpuInfo
import com.mories.performance_monitor.data.model.FpsInfo
import com.mories.performance_monitor.data.model.RamInfo
import com.mories.performance_monitor.data.model.SpeedTestResult

data class HomeState(
    val cpuInfo: CpuInfo? = null,
    val ramInfo: RamInfo? = null,
    val speedTestResult: SpeedTestResult? = null,
    val fpsInfo: FpsInfo = FpsInfo(0.0),
)