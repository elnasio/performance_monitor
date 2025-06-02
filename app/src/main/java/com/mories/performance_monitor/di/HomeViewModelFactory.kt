package com.mories.performance_monitor.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mories.performance_monitor.data.repository.PerformanceRepository
import com.mories.performance_monitor.domain.usecase.GetBatteryHealthInfo
import com.mories.performance_monitor.domain.usecase.GetCpuUsage
import com.mories.performance_monitor.domain.usecase.GetInternetSpeed
import com.mories.performance_monitor.domain.usecase.GetRamUsage
import com.mories.performance_monitor.domain.usecase.StartFpsMonitor
import com.mories.performance_monitor.domain.usecase.StopFpsMonitor
import com.mories.performance_monitor.ui.screen.viewmodel.HomeViewModel

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val repo = PerformanceRepository(context)
            return HomeViewModel(
                getCpuUsage = GetCpuUsage(repo),
                getRamUsage = GetRamUsage(repo),
                getInternetSpeed = GetInternetSpeed(),
                stopFpsMonitor = StopFpsMonitor(repo),
                startFpsMonitor = StartFpsMonitor(repo),
                getBatteryHealthInfo = GetBatteryHealthInfo(repo)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}