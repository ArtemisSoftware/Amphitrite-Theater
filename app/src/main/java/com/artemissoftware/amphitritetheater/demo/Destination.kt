package com.artemissoftware.amphitritetheater.demo

import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SHARED_ELEMENT_TRANSITION_GRAPH

sealed class Destination(val route: String, val title: String) {
    data object Home : Destination(route = "home", title = "Demo")
    data object SharedElementTransition : Destination(route = SHARED_ELEMENT_TRANSITION_GRAPH, title = "Shared Element Transition")
    data object Counter : Destination(route = "counter", title = "Animated counter")
    data object WebBrowser : Destination(route = "web_browser", title = "Web Browser")
    data object WebView : Destination(route = "web_view", title = "Web View")
    data object Stopwatch : Destination(route = "stopwatch", title = "Stopwatch")
    data object Speedometer : Destination(route = "speedometer", title = "Speedometer")
    data object Battery : Destination(route = "battery", title = "Battery")
    data object BubbleSort : Destination(route = "bubblesort", title = "Bubblesort")
    data object ThreeDGraph : Destination(route = "three_dimension", title = "3D Graph")
    data object ColorPalette : Destination(route = "color_palette", title = "Color Palette")
    data object DynamicTabSelector : Destination(route = "dynamic_tab_selector", title = "Dynamic Tab Selector")
    data object Shapes : Destination(route = "shapes", title = "Shapes")
    data object WebViewCustom : Destination(route = "web_view_custom", title = "Web View Custom")

    companion object{
        val demos = listOf(
            SharedElementTransition,
            Counter,
            WebBrowser,
            WebView,
            Stopwatch,
            Speedometer,
            Battery,
            BubbleSort,
            ThreeDGraph,
            ColorPalette,
            DynamicTabSelector,
            Shapes,
            WebViewCustom
        ).sortedBy { it.title }
    }
}