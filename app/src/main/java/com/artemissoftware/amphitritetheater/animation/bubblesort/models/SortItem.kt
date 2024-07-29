package com.artemissoftware.amphitritetheater.animation.bubblesort.models

import androidx.compose.ui.graphics.Color

data class SortItem(
    val id: Int,
    val isCurrentlyCompared: Boolean,
    val value: Int,
    val color: Color,
)
