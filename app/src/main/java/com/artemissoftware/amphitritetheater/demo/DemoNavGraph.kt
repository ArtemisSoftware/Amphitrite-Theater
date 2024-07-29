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
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SHARED_ELEMENT_TRANSITION_GRAPH
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SharedElementTransitionNavGraph
import com.artemissoftware.amphitritetheater.web.webbrowser.WebBrowserScreen
import com.artemissoftware.amphitritetheater.web.webview.WebViewScreen

@Composable
fun DemoNavGraph(
    navController: NavHostController,
    bubbleSortViewModel: BubbleSortViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
    ) {
        composable(route = Destination.Home.route) {
            DemoSelectorScreen(navController)
        }

        composable(route = SHARED_ELEMENT_TRANSITION_GRAPH) {
            SharedElementTransitionNavGraph(navController = rememberNavController())
        }

        composable(route = Destination.Counter.route) {
            CounterApp()
        }

        composable(route = Destination.WebBrowser.route) {
            WebBrowserScreen()
        }

        composable(route = Destination.WebView.route) {
            WebViewScreen()
        }

        composable(route = Destination.Stopwatch.route) {
            StopwatchScreen()
        }

        composable(route = Destination.Speedometer.route) {
            SpeedometerScreen()
        }

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

        composable(route = Destination.Bubblesort.route) {
            BubbleSortScreen(viewmodel = bubbleSortViewModel)
        }
    }
}
