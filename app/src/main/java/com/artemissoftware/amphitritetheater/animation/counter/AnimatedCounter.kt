package com.artemissoftware.amphitritetheater.animation.counter

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun AnimatedCounter(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayMedium
) {
    var oldCount by remember {
        mutableStateOf(count)
    }

    SideEffect {
        oldCount = count
    }

    Row(modifier = modifier) {

        val countString = count.toString()
        val oldCountString = oldCount.toString()

        for(i in countString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]

            val char = if(oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }

            AnimatedContent(
                targetState = char,
                label = "",
                transitionSpec = {
                    if (oldCount < count) {
                        slideInVertically { it } togetherWith slideOutVertically { -it }
                    } else {
                        slideInVertically { -it } togetherWith slideOutVertically { it }
                    }
                }
            ) { text ->
                Text(
                    text = text.toString(),
                    style = style,
                    softWrap = false
                )
            }
        }
    }
}

@Composable
internal fun AnimatedCounterV2(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayMedium
) {
    Row(modifier = modifier) {

        count.toString().forEach { char ->
            AnimatedContent(
                targetState = char,
                label = "",
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            ) {
                Text(
                    text = char.toString(),
                    style = style
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimatedCounterPreview() {
    AmphitriteTheaterTheme {
        AnimatedCounter(
            count = 9,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}