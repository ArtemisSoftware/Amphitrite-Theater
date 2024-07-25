package com.artemissoftware.amphitritetheater.battery

import androidx.compose.ui.geometry.Offset

data class ChargeInfo(
    private val canvasWidth: Float,
    private val canvasHeight: Float,
    private val outerThickness: Float,
    private val totalBarSpace: Float,
    private val steps: Int
){
    val spaceBetween = totalBarSpace / (steps + 1)
    val loadingBarWidth = (canvasWidth - outerThickness - totalBarSpace) / steps

    var currentStartOffset = Offset(
        x = (outerThickness / 2f) + (loadingBarWidth / 2f) + spaceBetween,
        y = outerThickness
    )

    var currentEndOffset = Offset(
        x = (outerThickness /2f) + (loadingBarWidth / 2f)  + spaceBetween,
        y = canvasHeight - outerThickness
    )




}
