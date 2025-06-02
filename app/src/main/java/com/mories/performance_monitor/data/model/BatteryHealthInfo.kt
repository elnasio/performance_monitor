package com.mories.performance_monitor.data.model

data class BatteryHealthInfo(
    val level: Int, // percentage (0-100)
    val temperature: Float, // in °C
    val voltage: Int, // in mV
    val healthStatus: String
)