package com.artemissoftware.amphitritetheater.shapes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.ui.theme.Blue10
import com.artemissoftware.amphitritetheater.ui.theme.BlueGray
import com.artemissoftware.amphitritetheater.ui.theme.Green
import com.artemissoftware.amphitritetheater.ui.theme.Orange
import com.artemissoftware.amphitritetheater.ui.theme.WarmBlue

@Composable
fun ShapeScreen() {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Square(side = 50.dp, color = WarmBlue)
            Star(starSize = 100.dp, color = Green)
            Circle(radius = 50.dp, color = BlueGray)
        }

        Box(modifier = Modifier){
            Circle(radius = 85.dp, color = BlueGray, modifier = Modifier.align(Alignment.Center))
            Star(starSize = 140.dp, color = Green, modifier = Modifier.align(Alignment.Center))
            Square(side = 50.dp, color = WarmBlue, modifier = Modifier.align(Alignment.Center))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Triangle(triangleHeight = 100.dp, color = WarmBlue)
            Pentagon(
                pentagonHeight = 120.dp,
                color = Green
            )
            Hexagon(
                hexagonHeight = 140.dp,
                color = BlueGray
            )
        }
        Box(modifier = Modifier){
            Triangle(triangleHeight = 160.dp, color = Blue10, modifier = Modifier.align(Alignment.Center))
            Pentagon(
                pentagonHeight = 120.dp,
                color = Green,
                modifier = Modifier.align(Alignment.Center)
            )
            Hexagon(
                hexagonHeight = 100.dp,
                color = BlueGray,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SquarePreview() {
    AmphitriteTheaterTheme {
        ShapeScreen()
    }
}