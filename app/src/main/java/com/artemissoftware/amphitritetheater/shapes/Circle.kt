package com.artemissoftware.amphitritetheater.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.ui.theme.Orange

@Composable
fun Circle(
    color: Color,
    radius: Dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(radius * 2)) {
        drawCircle(
            color = color,
            radius =  radius.toPx(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SquarePreview() {
    AmphitriteTheaterTheme {
        Row {
            Circle(
                radius = 200.dp,
                color = Orange,
            )
        }
    }
}