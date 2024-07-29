package com.artemissoftware.amphitritetheater.animation.bubblesort

import androidx.compose.ui.graphics.Color
import com.artemissoftware.amphitritetheater.animation.bubblesort.models.SortItem

internal object PreviewData {
    val sortItem = SortItem(
        id = 1,
        isCurrentlyCompared = false,
        value = 6,
        color = Color.LightGray
    )

    val sortItem2 = SortItem(
        id = 2,
        isCurrentlyCompared = false,
        value = 2,
        color = Color.LightGray
    )

    val sortItem3 = SortItem(
        id = 3,
        isCurrentlyCompared = true,
        value = 1,
        color = Color.LightGray
    )

    val sortList = listOf(sortItem, sortItem2)
}