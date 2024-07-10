package com.artemissoftware.amphitritetheater.demo

sealed class Destination(val route: String, val title: String) {
    data object SharedElementTransition : Destination(route = "shared_element_transition", title = "Shared Element Transition")
}