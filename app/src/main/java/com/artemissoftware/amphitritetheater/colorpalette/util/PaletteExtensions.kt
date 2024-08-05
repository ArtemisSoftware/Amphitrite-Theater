package com.artemissoftware.amphitritetheater.colorpalette.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

fun Palette.toBrush(): Brush {

    val startColor = dominantSwatch?.rgbAsColor() ?: Color.White
    val endColor = darkMutedSwatch?.rgbAsColor() ?: Color.White

    return Brush.linearGradient(colors = listOf(startColor, endColor))
}

fun Palette.Swatch.rgbAsColor(): Color = Color(rgb)
fun Palette.Swatch.titleRGBAsColor(): Color = Color(titleTextColor)
