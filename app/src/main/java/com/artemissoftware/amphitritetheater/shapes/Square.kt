package com.artemissoftware.amphitritetheater.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun Square(
    side: Dp,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(height = side, width = side)) {
        drawRect(
            color = color,
            size = Size(width = side.toPx(), height = side.toPx()),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SquarePreview() {
    AmphitriteTheaterTheme {
        Row {
            Square(
                side = 200.dp,
                color = Orange,
            )
        }
    }
}
