package com.artemissoftware.amphitritetheater.animation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.R
import com.artemissoftware.amphitritetheater.animation.counter.AnimatedCounter
import com.artemissoftware.amphitritetheater.animation.counter.AnimatedCounterV2
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun CounterApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        var count by remember {
            mutableStateOf(0)
        }
        var secondCounter by remember {
            mutableStateOf(0)
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedCounterV2(
                    count = count,
                    style = MaterialTheme.typography.displayLarge
                )
                Button(
                    onClick = {
                        count++
                    }
                ) {
                    Text(text = stringResource(id = R.string.increment))
                }
            }


            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedCounter(
                    count = secondCounter,
                    style = MaterialTheme.typography.displayLarge
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            secondCounter++
                        }
                    ) {
                        Text(text = stringResource(id = R.string.increment))
                    }
                    Button(
                        onClick = {
                            secondCounter--
                        }
                    ) {
                        Text(text = stringResource(id = R.string.decrement))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CounterAppPreview() {
    AmphitriteTheaterTheme {
        CounterApp()
    }
}
