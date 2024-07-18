package com.artemissoftware.amphitritetheater.animation.speedometer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SpeedometerScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var value by remember { mutableIntStateOf(0) }

        Speedometer(
            indicatorValue = value
        )

        OutlinedTextField(
            value = value.toString(),
            onValueChange = {
                value = if(it.isNotEmpty()){
                    it.toInt()
                } else 0
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SpeedometerScreenPreview() {
    SpeedometerScreen()
}
