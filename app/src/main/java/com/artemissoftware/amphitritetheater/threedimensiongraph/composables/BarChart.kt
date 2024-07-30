package com.artemissoftware.amphitritetheater.threedimensiongraph.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.threedimensiongraph.PreviewData
import com.artemissoftware.amphitritetheater.threedimensiongraph.models.BarInfo
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun BarChart(
    bars: List<BarInfo>,
    showDescription: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ){
        val listSum by remember {
            mutableIntStateOf(bars.sumOf { it.value })
        }

        bars.forEach { bar ->
            val percentage = bar.value / listSum.toFloat()

            Bar(
                modifier = Modifier
                    .height(120.dp * percentage * bars.size)
                    .width(40.dp),
                primaryColor = bar.color,
                percentage = bar.value / listSum.toFloat(),
                description = bar.description,
                showDescription = showDescription
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChartPreview() {
    AmphitriteTheaterTheme {
        BarChart(
            bars = PreviewData.bars,
            modifier = Modifier,
            showDescription = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChart2Preview() {
    AmphitriteTheaterTheme {
        BarChart(
            bars = PreviewData.bars2,
            modifier = Modifier.fillMaxWidth(),
            showDescription = true,
        )
    }
}
