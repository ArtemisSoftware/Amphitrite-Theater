package com.artemissoftware.amphitritetheater.demo

import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SHARED_ELEMENT_TRANSITION_GRAPH

sealed class Destination(val route: String, val title: String) {
    data object Home : Destination(route = "home", title = "Demo")
    data object SharedElementTransition : Destination(route = SHARED_ELEMENT_TRANSITION_GRAPH, title = "Shared Element Transition")
    data object Counter : Destination(route = "counter", title = "Animated counter")
    data object WebBrowser : Destination(route = "web_browser", title = "Web Browser")
    data object WebView : Destination(route = "web_view", title = "Web View")

    companion object{
        val demos = listOf(
            SharedElementTransition,
            Counter,
            WebBrowser,
            WebView
        )
    }
}