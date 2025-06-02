package com.mories.performance_monitor.core.network

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.mories.performance_monitor.core.extensions.hasPermissions

object NetworkUtils {

    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    fun isNetworkAvailable(context: Context): Boolean {
        if (!context.hasPermissions(*requiredPermissions)) return false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork ?: return false
        val networkCapabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}