package com.mories.performance_monitor.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mories.performance_monitor.data.model.CpuInfo
import com.mories.performance_monitor.presentation.component.AppToolbar

@Composable
fun DetailRoute(navController: NavController, cpuInfo: CpuInfo) {
    DetailScreen(cpuInfo, onBack = { navController.popBackStack() })
}

@Composable
fun DetailScreen(cpuInfo: CpuInfo, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "CPU Detail", onBackClick = onBack
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("CPU Name: ${cpuInfo.deviceName}")
            Text("Architecture: ${cpuInfo.architecture}")
            Text("Core Count: ${cpuInfo.coreCount}")
            Text("Usage: %.2f%%".format(cpuInfo.usagePercent))
            Text("Max Frequency: ${cpuInfo.maxFrequencyMHz} MHz")
            Text("Current Frequency: ${cpuInfo.currentFrequencyMHz} MHz")
            Text("Vendor: ${cpuInfo.vendor}")
        }
    }
}