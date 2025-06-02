package com.mories.performance_monitor.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider

object AppModule {

    fun provideHomeViewModelFactory(context: Context): ViewModelProvider.Factory {
        return HomeViewModelFactory(context)
    }
}