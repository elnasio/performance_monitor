package com.mories.performance_monitor.core.performance

import android.os.Build
import java.io.File

object SystemInfoUtil {

    fun getDeviceName(): String {
        return "${Build.MANUFACTURER} ${Build.MODEL}"
    }

    fun getAndroidVersion(): String {
        return "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
    }

    fun getCpuArchitecture(): String {
        return Build.SUPPORTED_ABIS.joinToString(", ")
    }
    fun getCpuMaxFrequencyMHz(): Int {
        return try {
            val path = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            val value = File(path).readText().trim()
            value.toInt() / 1000
        } catch (e: Exception) {
            -1
        }
    }

    fun getCpuCurrentFrequencyMHz(): Int {
        return try {
            val path = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"
            File(path).readText().trim().toInt() / 1000
        } catch (e: Exception) {
            -1
        }
    }

    fun getCpuVendor(): String {
        return try {
            val cpuInfo = File("/proc/cpuinfo").readText()
            cpuInfo.lineSequence()
                .firstOrNull { it.contains("Hardware") || it.contains("Processor") }?.split(":")
                ?.getOrNull(1)?.trim() ?: Build.HARDWARE.ifBlank { Build.BOARD }
        } catch (e: Exception) {
            Build.HARDWARE.ifBlank { Build.BOARD }
        }
    }
}