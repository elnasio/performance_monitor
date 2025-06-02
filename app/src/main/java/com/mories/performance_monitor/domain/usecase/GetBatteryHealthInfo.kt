package com.mories.performance_monitor.domain.usecase


import com.mories.performance_monitor.data.model.BatteryHealthInfo
import com.mories.performance_monitor.data.repository.PerformanceRepository

class GetBatteryHealthInfo(private val repository: PerformanceRepository) {
    operator fun invoke(): BatteryHealthInfo {
        return repository.getBatteryHealthInfo()
    }
}