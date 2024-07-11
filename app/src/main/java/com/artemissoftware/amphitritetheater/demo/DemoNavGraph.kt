package com.artemissoftware.amphitritetheater.demo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DemoNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
    ) {
        composable(route = Destination.Home.route) {
            DemoSelectorScreen(navController)
        }
    }
}