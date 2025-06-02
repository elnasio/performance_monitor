package com.mories.performance_monitor.domain.usecase

import com.mories.performance_monitor.data.repository.PerformanceRepository

class StopFpsMonitor(private val repository: PerformanceRepository) {
    operator fun invoke() {
        repository.stopFpsMonitor()
    }
}