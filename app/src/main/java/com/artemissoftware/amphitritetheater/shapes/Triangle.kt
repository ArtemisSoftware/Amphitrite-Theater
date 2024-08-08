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
fun Triangle(
    color: Color,
    triangleHeight: Dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(triangleHeight)) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(width / 2, 0F)
            lineTo(width, height)
            lineTo(0F, height)
            close()
        }

        drawPath(
            color = color,
            path = path
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SquarePreview() {
    AmphitriteTheaterTheme {
        Row {
            Triangle(
                triangleHeight = 300.dp,
                color = Orange
            )
        }
    }
}
