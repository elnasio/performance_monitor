package com.mories.performance_monitor.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mories.performance_monitor.ui.screen.viewmodel.HomeViewModel

@Composable
fun PerformanceCard(viewModel: HomeViewModel) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        CpuSection(viewModel)
        Spacer(modifier = Modifier.height(8.dp))

        RamSection(viewModel)
        Spacer(modifier = Modifier.height(8.dp))

        FpsSection(viewModel)
        Spacer(modifier = Modifier.height(8.dp))

        BatteryInfoSection(viewModel)
        Spacer(modifier = Modifier.height(8.dp))

        SpeedSection(viewModel)
    }
}

@Composable
fun BatteryInfoSection(viewModel: HomeViewModel) {
    val battery = viewModel.state.collectAsStateWithLifecycle().value.batteryHealthInfo

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Battery Health", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(12.dp))

            PerformanceSection(
                title = "Health", content = battery?.healthStatus ?: "-"
            )
            PerformanceSection(title = "Level", content = battery?.level?.let { "$it%" } ?: "-")
            PerformanceSection(
                title = "Temperature", content = battery?.temperature.toString()
            )
            PerformanceSection(
                title = "Voltage", content = battery?.voltage.toString()
            )
        }
    }
}

@Composable
private fun CpuSection(viewModel: HomeViewModel) {
    val cpuInfo = viewModel.state.collectAsStateWithLifecycle().value.cpuInfo
    PerformanceSection(
        title = "CPU Usage",
        content = cpuInfo?.usagePercent?.let { "%.2f%%".format(it) } ?: "-")
}

@Composable
private fun RamSection(viewModel: HomeViewModel) {
    val ramInfo = viewModel.state.collectAsStateWithLifecycle().value.ramInfo
    PerformanceSection(
        title = "RAM Usage",
        content = ramInfo?.usagePercent?.let { "%.2f%%".format(it) } ?: "-")
}

@Composable
private fun FpsSection(viewModel: HomeViewModel) {
    val fps = viewModel.state.collectAsStateWithLifecycle().value.fpsInfo
    PerformanceSection(
        title = "FPS", content = "%.2f".format(fps.fps)
    )
}

@Composable
private fun SpeedSection(viewModel: HomeViewModel) {
    val speed = viewModel.state.collectAsStateWithLifecycle().value.speedTestResult
    PerformanceSection(
        title = "Internet Speed",
        content = speed?.downloadSpeedMbps?.let { "%.2f Mbps".format(it) } ?: "-")
}

@Composable
private fun PerformanceSection(title: String, content: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = content, style = MaterialTheme.typography.bodyLarge)
        }
    }
}