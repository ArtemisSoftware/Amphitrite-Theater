package com.artemissoftware.amphitritetheater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.amphitritetheater.animation.bubblesort.BubbleSortViewModel
import com.artemissoftware.amphitritetheater.colorpalette.ColorPaletteViewModel
import com.artemissoftware.amphitritetheater.demo.DemoNavGraph
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.web.CustomWebViewModel
import com.artemissoftware.amphitritetheater.web.webviewcustom.WebViewCustomViewModel

class MainActivity : ComponentActivity() {

    private val bubbleSortViewModel = BubbleSortViewModel()
    private val colorPaletteViewModel by viewModels<ColorPaletteViewModel>()
    private val customWebViewModel by viewModels<CustomWebViewModel>()
    private val webViewCustomViewModel by viewModels<WebViewCustomViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphitriteTheaterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        DemoNavGraph(
                            colorPaletteViewModel = colorPaletteViewModel,
                            bubbleSortViewModel = bubbleSortViewModel,
                            customWebViewModel = customWebViewModel,
                            webViewCustomViewModel = webViewCustomViewModel,
                            navController = rememberNavController(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AmphitriteTheaterTheme {
        Greeting("Android")
    }
}