package com.artemissoftware.amphitritetheater.battery

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.ui.theme.Orange
import com.artemissoftware.amphitritetheater.ui.theme.Pink40
import kotlin.math.roundToInt

@Composable
fun Battery(
    chargePercentageValue:Int,
    modifier: Modifier = Modifier,
    numberOfChargeBars:Int = 10,
    outerThickness:Float = 30f,
    spaceBetweenChargeBars:Float = 16f,
    color: Color = Pink40,
    knobWidth:Float = 80f,
    height: Dp = 200.dp
) {

    Canvas(
        modifier = modifier
//            .padding(28.dp)
//            .offset(x = (-14).dp)
            .height(height)
    ){
        val canvasWidth = size.width
        val canvasHeight = size.height
        val canvasWidthLOLO = size.width - knobWidth
        val total = spaceBetweenChargeBars * numberOfChargeBars

        val chargeInfo = ChargeInfo(
            canvasWidth = canvasWidthLOLO,
            canvasHeight = canvasHeight,
            outerThickness = outerThickness,
            totalBarSpace = total,
            steps = numberOfChargeBars
        )

        case(
            width = canvasWidthLOLO,
            height = canvasHeight,
            color = color,
            borderThickness = outerThickness
        )

        with(chargeInfo){
            for(i in 0 until getNumberOfCharges(value = chargePercentageValue, steps = numberOfChargeBars)){
                charge(
                    color = color,
                    thickness = loadingBarWidth,
                    start = currentStartOffset,
                    end = currentEndOffset
                )

                currentStartOffset =
                    currentStartOffset.copy(x = currentStartOffset.x  + loadingBarWidth  + spaceBetween)

                currentEndOffset =
                    currentEndOffset.copy(x = currentEndOffset.x  + loadingBarWidth + spaceBetween)
            }
        }

        knob(
            width = knobWidth,
            height = canvasHeight * 0.5f,
            topLeft = Offset(x = canvasWidthLOLO, y = canvasHeight * 0.25F),
            color = color,
        )

// Guide for drawing
//        case(
//            width = canvasWidth,
//            height = canvasHeight,
//            color = Color.Red,
//            borderThickness = 1F
//        )
    }
}

private fun getNumberOfCharges(value: Int, steps: Int) = (value /100f * steps).roundToInt()

private fun DrawScope.case(
    width: Float,
    height: Float,
    color: Color,
    borderThickness: Float,
){
    drawRect(
        color = color,
        size = Size(
            width = width,
            height = height
        ),
        style = Stroke(
            width = borderThickness,
            pathEffect = PathEffect.cornerPathEffect(10.dp.toPx())
        )
    )
}

private fun DrawScope.knob(
    width: Float,
    height: Float,
    color: Color,
    topLeft:  Offset = Offset(x = 0F, y = 0F),
){
    drawRoundRect(
        color = color,
        topLeft = topLeft,
        size = Size(width = width, height = height),
        cornerRadius = CornerRadius(15f,15f)
    )
}

private fun DrawScope.charge(
    color: Color,
    thickness: Float,
    start:  Offset = Offset(x = 0F, y = 0F),
    end:  Offset = Offset(x = 0F, y = 0F)
){
    drawLine(
        color = color,
        strokeWidth = thickness,
        start = start,
        end = end
    )
}


@Preview(showBackground = true)
@Composable
private fun BatteryPreview() {
    AmphitriteTheaterTheme {

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Battery(
                modifier = Modifier
                    .fillMaxWidth(),
                chargePercentageValue = 20
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Battery2Preview() {
    AmphitriteTheaterTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Battery(
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth(),
                outerThickness = 30f,
                knobWidth = 100f,
                spaceBetweenChargeBars = 16f,
                chargePercentageValue = 100,
                numberOfChargeBars = 10
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Battery3Preview() {
    AmphitriteTheaterTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Battery(
                chargePercentageValue = 30,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5F),
            )
            Battery(
                color = Orange,
                chargePercentageValue = 100,
                height = 160.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5F),
            )
        }
    }
}
