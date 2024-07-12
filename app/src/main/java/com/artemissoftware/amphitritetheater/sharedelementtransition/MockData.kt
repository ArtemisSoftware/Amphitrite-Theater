package com.artemissoftware.amphitritetheater.sharedelementtransition

import com.artemissoftware.amphitritetheater.sharedelementtransition.models.ImageElement

internal object MockData {
    val images = listOf(
        ImageElement(
            id = 1,
            url = "https://images.unsplash.com/photo-1618245062405-03214ac53f0c?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            author = "Nathan Cima",
            location = "Isla Mujeres, Quintana Roo, Mexique",
        ),
        ImageElement(
            id = 2,
            url = "https://images.unsplash.com/photo-1528652899333-492965af4854?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            author = "Andre Ouellet",
            location = "St-Félicien, Canada",
        ),
        ImageElement(
            id = 3,
            url = "https://images.unsplash.com/photo-1484312152213-d713e8b7c053?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            author = "Jonatan Pie",
            location = "The wild",
        ),
    )
}