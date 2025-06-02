package com.mories.performance_monitor.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mories.performance_monitor.ui.screen.viewmodel.HomeViewModel

@Composable
fun PerformanceCard(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        PerformanceSection(
            title = "CPU Usage",
            content = state.cpuInfo?.usagePercent?.let { "%.2f%%".format(it) } ?: "-")
        Spacer(modifier = Modifier.height(8.dp))

        PerformanceSection(
            title = "RAM Usage",
            content = state.ramInfo?.usagePercent?.let { "%.2f%%".format(it) } ?: "-")
        Spacer(modifier = Modifier.height(8.dp))

        PerformanceSection(
            title = "Internet Speed",
            content = state.speedTestResult?.downloadSpeedMbps?.let { "%.2f Mbps".format(it) }
                ?: "-")
    }
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