package com.mories.performance_monitor.core.performance

import android.os.Build

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
}