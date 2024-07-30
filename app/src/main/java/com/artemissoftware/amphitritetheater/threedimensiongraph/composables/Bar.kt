package com.artemissoftware.amphitritetheater.threedimensiongraph.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.ui.theme.White
import kotlin.math.roundToInt

@Composable
internal fun Bar(
    primaryColor: Color,
    percentage: Float,
    description: String,
    showDescription: Boolean,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.fillMaxSize()
        ){
            val width = size.width
            val height = size.height
            val barWidth = width / 5 * 3
            val barHeight = height / 8 * 7
            val barHeight3DPart = height - barHeight
            val barWidth3DPart = (width - barWidth)*(height * 0.002f)

            front(
                canvasHeight = height,
                barWidth = barWidth,
                barHeight = barHeight,
                primaryColor = primaryColor
            )

            rightSide(
                canvasHeight = height,
                barWidth = barWidth,
                barHeight = barHeight,
                barWidth3DPart = barWidth3DPart,
                primaryColor = primaryColor
            )

            top(
                barWidth = barWidth,
                barHeight3DPart = barHeight3DPart,
                barWidth3DPart = barWidth3DPart,
                primaryColor = primaryColor
            )

            bottomDescription(
                barWidth = barWidth,
                percentage = percentage,
                canvasHeight = height
            )

            topDescription(
                description = description,
                barWidth3DPart = barWidth3DPart,
                showDescription = showDescription
            )
        }
    }
}
private fun DrawScope.topDescription(
    description: String,
    barWidth3DPart: Float,
    showDescription: Boolean,
){
    if(showDescription){
        drawContext.canvas.nativeCanvas.apply {
            rotate(-50f, pivot = Offset(barWidth3DPart - 20,0f)){
                drawText(
                    description,
                    0f,
                    0f,
                    android.graphics.Paint().apply {
                        this.color = Color.Black.toArgb()
                        textSize = 14.dp.toPx()
                        isFakeBoldText = true
                    }
                )
            }
        }
    }
}

private fun DrawScope.bottomDescription(
    barWidth: Float,
    percentage: Float,
    canvasHeight: Float,
){
    drawContext.canvas.nativeCanvas.apply {
        drawText(
            "${(percentage * 100).roundToInt()} %",
            barWidth / 5f,
            canvasHeight + 55f,
            android.graphics.Paint().apply {
                this.color = Color.Black.toArgb()
                textSize = 11.dp.toPx()
                isFakeBoldText = true
            }
        )
    }
}

private fun DrawScope.top(
    barWidth: Float,
    barHeight3DPart: Float,
    barWidth3DPart: Float,
    primaryColor: Color
){
    val path = Path().apply {
        moveTo(0f, barHeight3DPart)
        lineTo(barWidth, barHeight3DPart)
        lineTo(barWidth + barWidth3DPart,0f)
        lineTo(barWidth3DPart,0f)
        close()
    }

    drawPath(
        path,
        brush = Brush.linearGradient(
            colors = listOf(Gray, primaryColor)
        )
    )
}

private fun DrawScope.rightSide(
    canvasHeight: Float,
    barWidth: Float,
    barHeight: Float,
    barWidth3DPart: Float,
    primaryColor: Color
){
    val path = Path().apply {
        moveTo(barWidth,canvasHeight - barHeight)
        lineTo(barWidth3DPart + barWidth,0f)
        lineTo(barWidth3DPart + barWidth, barHeight)
        lineTo(barWidth, canvasHeight)
        close()
    }

    drawPath(
        path,
        brush = Brush.linearGradient(
            colors = listOf(primaryColor, Color.Blue)
        )
    )
}


private fun DrawScope.front(
    canvasHeight: Float,
    barWidth: Float,
    barHeight: Float,
    primaryColor: Color
){
    val path = Path().apply {
        moveTo(0f, canvasHeight)
        lineTo(barWidth, canvasHeight)
        lineTo(barWidth,canvasHeight - barHeight)
        lineTo(0f,canvasHeight - barHeight)
        close()
    }

    drawPath(
        path = path,
        brush = Brush.linearGradient(
            colors = listOf(Color.Red/*gray*/, primaryColor)
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun BarPreview() {
    AmphitriteTheaterTheme {
        Bar(
            primaryColor = Color.Yellow,
            percentage = 20F,
            description = "Chart",
            showDescription = true
        )
    }
}
