package com.artemissoftware.amphitritetheater.demo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.amphitritetheater.animation.bubblesort.BubbleSortScreen
import com.artemissoftware.amphitritetheater.animation.bubblesort.BubbleSortViewModel
import com.artemissoftware.amphitritetheater.animation.counter.CounterApp
import com.artemissoftware.amphitritetheater.animation.speedometer.SpeedometerScreen
import com.artemissoftware.amphitritetheater.animation.stopwatch.StopwatchScreen
import com.artemissoftware.amphitritetheater.battery.Battery
import com.artemissoftware.amphitritetheater.colorpalette.ColorPaletteScreen
import com.artemissoftware.amphitritetheater.colorpalette.ColorPaletteViewModel
import com.artemissoftware.amphitritetheater.dynamictabselector.DynamicTabSelector
import com.artemissoftware.amphitritetheater.shapes.ShapeScreen
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SHARED_ELEMENT_TRANSITION_GRAPH
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SharedElementTransitionNavGraph
import com.artemissoftware.amphitritetheater.threedimensiongraph.ThreeDimensionGraphScreen
import com.artemissoftware.amphitritetheater.web.CustomWebViewModel
import com.artemissoftware.amphitritetheater.web.webbrowser.WebBrowserScreen
import com.artemissoftware.amphitritetheater.web.webview.WebViewScreen
import com.artemissoftware.amphitritetheater.web.webviewcustom.WebViewCustomScreen
import com.artemissoftware.amphitritetheater.web.webviewcustom.WebViewCustomViewModel

@Composable
fun DemoNavGraph(
    navController: NavHostController,
    bubbleSortViewModel: BubbleSortViewModel,
    colorPaletteViewModel: ColorPaletteViewModel,
    customWebViewModel: CustomWebViewModel,
    webViewCustomViewModel: WebViewCustomViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
    ) {

        //B

        composable(route = Destination.Battery.route) {
            SingleContent {
                Row {
                    Battery(
                        chargePercentageValue = 30,
                        modifier = Modifier
                            .fillMaxWidth(0.7F),
                    )
                }
            }
        }

        composable(route = Destination.BubbleSort.route) {
            BubbleSortScreen(viewmodel = bubbleSortViewModel)
        }

        //Web

        composable(route = Destination.WebBrowser.route) {
            WebBrowserScreen()
        }

        composable(route = Destination.WebView.route) {
            WebViewScreen()
        }

        composable(route = Destination.WebViewCustom.route) {
            WebViewCustomScreen(webViewCustomViewModel)
        }

        //--

        composable(route = Destination.Home.route) {
            DemoSelectorScreen(navController)
        }

        composable(route = SHARED_ELEMENT_TRANSITION_GRAPH) {
            SharedElementTransitionNavGraph(navController = rememberNavController())
        }

        composable(route = Destination.Counter.route) {
            CounterApp()
        }



        composable(route = Destination.Stopwatch.route) {
            StopwatchScreen()
        }

        composable(route = Destination.Speedometer.route) {
            SpeedometerScreen()
        }


        composable(route = Destination.ThreeDGraph.route) {
            ThreeDimensionGraphScreen()
        }
        composable(route = Destination.ColorPalette.route) {
            ColorPaletteScreen(viewModel = colorPaletteViewModel)
        }
        composable(route = Destination.DynamicTabSelector.route) {
            SingleContent {
                DynamicTabSelector(
                    tabs = listOf("Tab 1", "Tab 2", "Tab 3"),
                    onTabSelected =  {}
                )
            }
        }
        composable(route = Destination.Shapes.route) {
            ShapeScreen()
        }
    }
}
