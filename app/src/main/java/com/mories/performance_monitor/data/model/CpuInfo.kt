package com.mories.performance_monitor.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CpuInfo(
    val usagePercent: Float,
    val coreCount: Int,
    val architecture: String,
    val deviceName: String,
    val maxFrequencyMHz: Int,
    val currentFrequencyMHz: Int,
    val vendor: String,
) : Parcelable