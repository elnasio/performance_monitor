package com.mories.performance_monitor.domain.usecase

import com.mories.performance_monitor.data.model.RamInfo
import com.mories.performance_monitor.data.repository.PerformanceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetRamUsage(private val repository: PerformanceRepository) {
    operator fun invoke(intervalMillis: Long = 1000): Flow<RamInfo> = flow {
        while (true) {
            emit(repository.getRamInfo())
            delay(intervalMillis)
        }
    }.flowOn(Dispatchers.Default)
}