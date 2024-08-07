package com.artemissoftware.amphitritetheater.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.ui.theme.Orange

@Composable
fun Star(
    color: Color,
    starSize: Dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(starSize)) {
        val with = size.width
        val height = size.height

//        val path = Path().apply {
//            moveTo(with / 2, height / 4)
//            lineTo(with / 2 + 150, height / 2 - 300)
//            lineTo(with / 2 + 500, height / 2 - 300)
//            lineTo(with / 2 + 250, height / 2 - 50)
//            lineTo(with / 2 + 400, height - 900)
//
//            lineTo(with / 2, height / 2 + 100)
//
//            lineTo(with / 2 - 400, height - 900)
//            lineTo(with / 2 - 250, height / 2 - 50)
//            lineTo(with / 2 - 500, height / 2 - 300)
//            lineTo(with / 2 - 150, height / 2 - 300)
//            close()
//        }

        val path = Path().apply {
            moveTo(with.toMeasure(50), 0F)
            lineTo(with.toMeasure(85), height)
            lineTo(with.toMeasure(50), height.toMeasure(75))
            lineTo(with.toMeasure(15), height )
            close()
        }

        val path2 = Path().apply {
            moveTo(0F, height.toMeasure(35))
            lineTo(with , height.toMeasure(35))
            lineTo(with.toMeasure(50), height.toMeasure(75))
            close()
        }

        drawPath(
            color = color,
            path = path
        )

        drawPath(
            color = color,
            path = path2
        )
    }
}

private fun Float.toMeasure(percentage: Int) = this * percentage / 100


@Preview(showBackground = true)
@Composable
private fun SquarePreview() {
    AmphitriteTheaterTheme {
        Row {
            Star(
                starSize = 300.dp,
                color = Orange
            )
        }
    }
}
