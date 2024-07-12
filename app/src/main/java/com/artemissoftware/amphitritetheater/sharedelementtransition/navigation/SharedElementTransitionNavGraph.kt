package com.artemissoftware.amphitritetheater.sharedelementtransition.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.artemissoftware.amphitritetheater.sharedelementtransition.ImageDetailScreen
import com.artemissoftware.amphitritetheater.sharedelementtransition.ImageListScreen
import com.artemissoftware.amphitritetheater.sharedelementtransition.navigation.NavArgument.IMAGE_ID

const val SHARED_ELEMENT_TRANSITION_GRAPH = "shared_element_transition_graph"

fun NavGraphBuilder.sharedElementTransitionNavGraph(
    navController: NavHostController
) {
    navigation(
        route = SHARED_ELEMENT_TRANSITION_GRAPH,
        startDestination = Route.ImageList.route,
    ) {

        composable(route = Route.ImageList.route) {
            ImageListScreen(
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
                imageId = imageId,
                onClick = {
                    navController.popBackStack()
                },
            )
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