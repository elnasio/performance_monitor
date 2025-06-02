package com.mories.performance_monitor.core.performance

import android.view.Choreographer

class FpsMonitor(private val callback: (fps: Double) -> Unit) {

    private var lastFrameTimeNanos = 0L
    private var frameCount = 0
    private var startTimeMillis = 0L

    private val choreographer = Choreographer.getInstance()
    private val frameCallback = object : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            if (lastFrameTimeNanos == 0L) {
                lastFrameTimeNanos = frameTimeNanos
                startTimeMillis = System.currentTimeMillis()
            } else {
                frameCount++
                val now = System.currentTimeMillis()
                val duration = now - startTimeMillis

                if (duration >= 1000) {
                    val fps = frameCount * 1000.0 / duration
                    callback(fps)
                    frameCount = 0
                    startTimeMillis = now
                }
            }

            choreographer.postFrameCallback(this)
        }
    }

    fun start() {
        choreographer.postFrameCallback(frameCallback)
    }

    fun stop() {
        choreographer.removeFrameCallback(frameCallback)
    }
}