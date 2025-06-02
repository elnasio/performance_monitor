package com.mories.performance_monitor.ui.screen.viewmodel

sealed class HomeEvent {
    data object LoadAll : HomeEvent()
}