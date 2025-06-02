package com.mories.performance_monitor.domain.usecase

import com.mories.performance_monitor.data.model.FpsInfo
import com.mories.performance_monitor.data.repository.PerformanceRepository

class StartFpsMonitor(private val repository: PerformanceRepository) {
    operator fun invoke(onUpdate: (FpsInfo) -> Unit) {
        repository.startFpsMonitor(onUpdate)
    }
}