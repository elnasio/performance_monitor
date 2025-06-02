package com.mories.performance_monitor.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mories.performance_monitor.data.model.CpuInfo

@Composable
fun DetailRoute(navController: NavController, cpuInfo: CpuInfo) {
    DetailScreen(cpuInfo)
}

@Composable
fun DetailScreen(cpuInfo: CpuInfo) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("CPU Name: ${cpuInfo.deviceName}")
        Text("Architecture: ${cpuInfo.architecture}")
        Text("Core Count: ${cpuInfo.coreCount}")
        Text("Usage: %.2f%%".format(cpuInfo.usagePercent))
    }
}