package com.artemissoftware.amphitritetheater.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.ui.theme.Orange

@Composable
fun Hexagon(
    color: Color,
    hexagonHeight: Dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(hexagonHeight)) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(width.toMeasure(20), 0F)
            lineTo(width.toMeasure(80), 0F)
            lineTo(width, height.toMeasure(50))
            lineTo(width.toMeasure(80), height)
            lineTo(width.toMeasure(20), height)
            lineTo(0F, height.toMeasure(50))
            close()
        }

        drawPath(
            color = color,
            path = path
        )
    }
}

private fun Float.toMeasure(percentage: Int) = this * percentage / 100

@Preview(showBackground = true)
@Composable
private fun SquarePreview() {
    AmphitriteTheaterTheme {
        Row {
            Hexagon(
                hexagonHeight = 200.dp,
                color = Orange
            )
        }
    }
}
