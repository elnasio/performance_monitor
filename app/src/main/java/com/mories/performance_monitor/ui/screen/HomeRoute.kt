package com.mories.performance_monitor.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mories.performance_monitor.data.model.CpuInfo
import com.mories.performance_monitor.di.AppModule
import com.mories.performance_monitor.presentation.component.AppToolbar
import com.mories.performance_monitor.presentation.component.PerformanceCard
import com.mories.performance_monitor.presentation.navigation.Screen
import com.mories.performance_monitor.ui.screen.viewmodel.HomeEvent
import com.mories.performance_monitor.ui.screen.viewmodel.HomeViewModel

@Composable
fun HomeRoute(navController: NavController, onNavigateToDetail: (Screen) -> Unit) {
    val context = LocalContext.current
    val factory = remember { AppModule.provideHomeViewModelFactory(context) }
    val viewModel: HomeViewModel = viewModel(factory = factory)

    HomeScreen(
        viewModel = viewModel,
        onDetailClick = { cpuInfo ->
            navController.getBackStackEntry(Screen.Detail.route).savedStateHandle["cpuInfo"] =
                cpuInfo
        },
        onNavigateToDetail = {
            onNavigateToDetail.invoke(Screen.Detail)
        },
    )
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel, onDetailClick: (CpuInfo) -> Unit, onNavigateToDetail: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadAll)
    }

    val state by viewModel.state.collectAsState()
    val cpuInfo = state.cpuInfo
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            AppToolbar(
                title = "Performance Monitor", paddingBottom = 10,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            PerformanceCard(viewModel = viewModel)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    cpuInfo?.let {
                        onNavigateToDetail()
                        onDetailClick(it)
                    }
                }, enabled = cpuInfo != null
            ) {
                Text("View CPU Info")
            }
        }

    }
}