package com.artemissoftware.amphitritetheater.animation.stopwatch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class StopwatchViewModel : ViewModel() {
    val seconds = (0..100)
        .asSequence()
        .asFlow()
        .map {
            if (it in 0..9) "0$it" else it
        }
        .onEach { delay(1000) }
}