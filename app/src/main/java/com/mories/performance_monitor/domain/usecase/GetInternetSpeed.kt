package com.mories.performance_monitor.domain.usecase

import com.mories.performance_monitor.core.performance.InternetSpeedMonitor
import com.mories.performance_monitor.data.model.SpeedTestResult
import kotlinx.coroutines.flow.Flow

class GetInternetSpeed {
    operator fun invoke(): Flow<SpeedTestResult> {
        return InternetSpeedMonitor.observeSpeed()
    }
}