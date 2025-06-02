package com.mories.performance_monitor.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mories.performance_monitor.data.model.CpuInfo
import com.mories.performance_monitor.ui.screen.DetailRoute
import com.mories.performance_monitor.ui.screen.HomeRoute

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestination.Home.route
    ) {
        composable(route = AppDestination.Home.route) {
            HomeRoute(
                navController = navController,
                onNavigateToDetail = {
                    navController.navigate(it.route)
                },
            )
        }

        composable(route = Screen.Detail.route) { backStackEntry ->
            val cpuInfo = backStackEntry.savedStateHandle.get<CpuInfo>("cpuInfo")
            if (cpuInfo != null) {
                DetailRoute(navController, cpuInfo)
            }
        }
    }
}