package com.mories.performance_monitor.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mories.performance_monitor.domain.usecase.GetBatteryHealthInfo
import com.mories.performance_monitor.domain.usecase.GetCpuUsage
import com.mories.performance_monitor.domain.usecase.GetInternetSpeed
import com.mories.performance_monitor.domain.usecase.GetRamUsage
import com.mories.performance_monitor.domain.usecase.StartFpsMonitor
import com.mories.performance_monitor.domain.usecase.StopFpsMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCpuUsage: GetCpuUsage,
    private val getRamUsage: GetRamUsage,
    private val getInternetSpeed: GetInternetSpeed,
    private val startFpsMonitor: StartFpsMonitor,
    private val stopFpsMonitor: StopFpsMonitor,
    private val getBatteryHealthInfo: GetBatteryHealthInfo,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadAll -> loadAll()
        }
    }

    private fun loadAll() {
        startFpsMonitorInternal()
        viewModelScope.launch {
            launch {
                getCpuUsage().collect { cpu ->
                    _state.update { it.copy(cpuInfo = cpu) }
                }
            }

            launch {
                getRamUsage().collect { ram ->
                    _state.update { it.copy(ramInfo = ram) }
                }
            }

            launch {
                getInternetSpeed().collect { speed ->
                    _state.update { it.copy(speedTestResult = speed) }
                }
            }
            launch {
                val battery = runCatching { getBatteryHealthInfo() }.getOrNull()
                battery?.let {
                    _state.update { it.copy(batteryHealthInfo = battery) }
                }
            }
        }
    }

    private fun startFpsMonitorInternal() {
        startFpsMonitor { fps ->
            _state.update { it.copy(fpsInfo = fps) }
        }
    }

    private fun stopFpsMonitor() {
        stopFpsMonitor.invoke()
    }

    override fun onCleared() {
        super.onCleared()
        stopFpsMonitor()
    }
}