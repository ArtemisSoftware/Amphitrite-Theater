package com.artemissoftware.amphitritetheater.colorpalette.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

fun Palette.toBrush(): Brush{
    val startColor = this.dominantSwatch?.rgbAsColor() ?: Color.White
    val endColor = this.darkMutedSwatch?.rgbAsColor() ?: Color.White

    return Brush.linearGradient(colors = listOf(startColor, endColor))
}

private fun Palette.Swatch.rgbAsColor(): Color = Color(rgb)
private fun Palette.Swatch.titleRGBAsColor(): Color = Color(titleTextColor)
