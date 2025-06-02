package com.mories.performance_monitor.data.model

data class RamInfo(
    val totalMemBytes: Long,
    val usedMemBytes: Long,
    val availMemBytes: Long,
    val usagePercent: Float
)