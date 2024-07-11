package com.artemissoftware.amphitritetheater.demo

sealed class Destination(val route: String, val title: String) {
    data object Home : Destination(route = "home", title = "Demo")
    data object SharedElementTransition : Destination(route = "shared_element_transition", title = "Shared Element Transition")

    companion object{
        val demos = listOf(
            SharedElementTransition
        )
    }
}