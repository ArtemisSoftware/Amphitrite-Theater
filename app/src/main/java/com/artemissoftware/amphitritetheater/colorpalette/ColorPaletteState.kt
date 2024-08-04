package com.artemissoftware.amphitritetheater.colorpalette

import androidx.compose.ui.graphics.ImageBitmap
import androidx.palette.graphics.Palette

data class ColorPaletteState(
    val imageBitmap: ImageBitmap? = null,
    val colorPalette: Palette? = null
)
