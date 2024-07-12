@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.artemissoftware.amphitritetheater.sharedelementtransition.composables

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.artemissoftware.amphitritetheater.sharedelementtransition.MockData
import com.artemissoftware.amphitritetheater.sharedelementtransition.models.ImageElement
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun SharedTransitionScope.ImageElementItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    image: ImageElement,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row (
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(image.id) }
            .padding(8.dp)
    ){
        ImageElementItemContent(
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
                )
        )
    }
}

@Composable
private fun RowScope.ImageElementItemContent(
    image: ImageElement,
    @SuppressLint("ModifierParameter") imageSharedElementModifier: Modifier = Modifier,
    textSharedElementModifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = imageSharedElementModifier
            .size(80.dp)
            .clip(shape = RoundedCornerShape(size = 12.dp)),
        model = image.url,
        contentDescription = "image",
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.width(8.dp))

    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = image.location,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = textSharedElementModifier
                .alpha(0.5f),
            text = image.author,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageElementItemContentPreview() {
    AmphitriteTheaterTheme {
        Row {
            ImageElementItemContent(
                image = MockData.images[1],
            )
        }
    }
}