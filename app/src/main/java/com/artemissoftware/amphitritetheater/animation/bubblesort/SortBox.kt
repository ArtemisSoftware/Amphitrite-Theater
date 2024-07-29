package com.artemissoftware.amphitritetheater.animation.bubblesort

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.amphitritetheater.animation.bubblesort.models.SortItem
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun SortBox(
    sortItem: SortItem,
    size: Dp = 60.dp,
    modifier: Modifier = Modifier
) {
    val borderStroke = if(sortItem.isCurrentlyCompared){
        BorderStroke(width = 3.dp, color = Color.Black)
    }else{
        BorderStroke(width = 0.dp, color = Color.Blue)
    }
    Box(
        modifier = modifier
            .size(size)
            .background(sortItem.color, RoundedCornerShape(15.dp))
            .border(borderStroke, RoundedCornerShape(15.dp))
//            .animateItemPlacement(
//                tween(300)
//            )
        ,
        contentAlignment = Alignment.Center
    ){
        Text(
            "${sortItem.value}",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SortBoxPreview() {
    AmphitriteTheaterTheme {
        SortBox(
            sortItem = PreviewData.sortItem
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SortBoxCurrentlyComparedPreview() {
    AmphitriteTheaterTheme {
        SortBox(
            sortItem = PreviewData.sortItem3
        )
    }
}
