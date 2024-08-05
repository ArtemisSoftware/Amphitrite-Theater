package com.artemissoftware.amphitritetheater.colorpalette

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import com.artemissoftware.amphitritetheater.colorpalette.util.rgbAsColor
import com.artemissoftware.amphitritetheater.colorpalette.util.titleRGBAsColor
import com.artemissoftware.amphitritetheater.colorpalette.util.toBrush

data class ColorPalette(
    val colorPalette: Palette? = null
){
    fun getBackgroundColor() = colorPalette?.toBrush() ?: Brush.linearGradient(colors = listOf(Color.White, Color.White))
    fun getTitleTextColor() = colorPalette?.lightVibrantSwatch?.titleTextColor?.let(::Color) ?: Color.Black
    fun getTopBarColor() = colorPalette?.darkVibrantSwatch?.rgb?.let(::Color) ?: Color.White
    fun getButtonBackgroundColor() = colorPalette?.darkVibrantSwatch?.rgbAsColor() ?: Color.White
    fun getButtonContentColor() = colorPalette?.lightVibrantSwatch?.titleRGBAsColor() ?: Color.Black
}
