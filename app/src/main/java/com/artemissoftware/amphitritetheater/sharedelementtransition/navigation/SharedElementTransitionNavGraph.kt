@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.artemissoftware.amphitritetheater.sharedelementtransition.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artemissoftware.amphitritetheater.sharedelementtransition.ImageDetailScreen
import com.artemissoftware.amphitritetheater.sharedelementtransition.ImageListScreen
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.NavArgument.IMAGE_ID

const val SHARED_ELEMENT_TRANSITION_GRAPH = "shared_element_transition_graph"

@Composable
fun SharedElementTransitionNavGraph(
    navController: NavHostController
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            route = SHARED_ELEMENT_TRANSITION_GRAPH,
            startDestination = Route.ImageList.route,
        ) {

            composable(route = Route.ImageList.route) {
                ImageListScreen(
                    animatedVisibilityScope = this@composable,
                    onImageClick = { imageId ->
                        navController.navigate(Route.ImageDetail.passImageId(imageId))
                    },
                )
            }

            composable(
                route = Route.ImageDetail.route,
                arguments = listOf(
                    navArgument(name = IMAGE_ID){
                        type = NavType.IntType
                    }
                )
            ) {
                val imageId = it.arguments?.getInt(IMAGE_ID) ?: 1
                ImageDetailScreen(
                    animatedVisibilityScope = this@composable,
                    imageId = imageId,
                    onClick = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}

internal object NavArgument{
    const val IMAGE_ID = "image_id"
}

internal sealed class Route(val route: String){
    data object ImageList: Route(route = "image_list")
    data object ImageDetail: Route(route = "image_detail/{$IMAGE_ID}"){
        fun passImageId(id: Int) = "image_detail/$id"
    }
}