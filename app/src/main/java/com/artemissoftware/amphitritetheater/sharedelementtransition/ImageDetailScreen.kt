@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.artemissoftware.amphitritetheater.sharedelementtransition

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.artemissoftware.amphitritetheater.sharedelementtransition.models.ImageElement
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun SharedTransitionScope.ImageDetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageId: Int,
    onClick: () -> Unit
) {
    val currentImage by remember {
        mutableStateOf(MockData.images.find { it.id == imageId})
    }
    currentImage?.let { image ->
        Column(modifier = Modifier.fillMaxSize()) {
            ImageDetailScreenContent(
                image = image,
                imageSharedElementModifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-${image.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                textSharedElementModifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "text-${image.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    ),
                onClick = onClick
            )
        }
    }
}

@Composable
private fun ColumnScope.ImageDetailScreenContent(
    image: ImageElement,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter") imageSharedElementModifier: Modifier = Modifier,
    textSharedElementModifier: Modifier = Modifier,
) {
    AsyncImage(
        modifier = imageSharedElementModifier
            .fillMaxWidth()
            .height(350.dp)
            .clickable {
                onClick()
            },
        model = image.url,
        contentDescription = "Image",
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = textSharedElementModifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            modifier = Modifier,
            text = image.author,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageDetailScreenContentPreview() {
    AmphitriteTheaterTheme {

        Column(modifier = Modifier.fillMaxSize()) {
            ImageDetailScreenContent(
                image = MockData.images[1],
                onClick = {},
            )
        }
    }
}
