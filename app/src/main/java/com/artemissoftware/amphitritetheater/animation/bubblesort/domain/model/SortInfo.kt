package com.artemissoftware.amphitritetheater.animation.bubblesort.domain.model

data class SortInfo(
    val currentItem:Int,
    val shouldSwap:Boolean,
    val hadNoEffect:Boolean,
    val interactionInfo: InteractionInfo
)
