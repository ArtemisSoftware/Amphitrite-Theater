@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class
)

package com.artemissoftware.amphitritetheater.sharedelementtransition

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.sharedelementtransition.composables.ImageElementItem
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun SharedTransitionScope.ImageListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onImageClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = MockData.images,
            key = { it.id }
        ){ image ->
            ImageElementItem(
                animatedVisibilityScope = animatedVisibilityScope,
                modifier = Modifier.fillMaxWidth(),
                image = image,
                onClick = onImageClick
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ImageListScreenPreview() {
    AmphitriteTheaterTheme {
//        ImageListScreen(
//            onImageClick = {}
//        )
    }
}