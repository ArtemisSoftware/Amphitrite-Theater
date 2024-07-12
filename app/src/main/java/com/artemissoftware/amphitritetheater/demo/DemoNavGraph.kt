package com.artemissoftware.amphitritetheater.demo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SHARED_ELEMENT_TRANSITION_GRAPH
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.SharedElementTransitionNavGraph

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

        composable(route = SHARED_ELEMENT_TRANSITION_GRAPH) {
            SharedElementTransitionNavGraph(navController = rememberNavController())
        }
    }
}