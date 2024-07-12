package com.artemissoftware.amphitritetheater.demo

import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SHARED_ELEMENT_TRANSITION_GRAPH

sealed class Destination(val route: String, val title: String) {
    data object Home : Destination(route = "home", title = "Demo")
    data object SharedElementTransition : Destination(route = SHARED_ELEMENT_TRANSITION_GRAPH, title = "Shared Element Transition")

    companion object{
        val demos = listOf(
            SharedElementTransition
        )
    }
}