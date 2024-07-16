@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.artemissoftware.amphitritetheater.web.webbrowser

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.Greeting
import com.artemissoftware.amphitritetheater.R
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.web.WebConstants
import com.kevinnzou.web.AccompanistWebViewClient
import com.kevinnzou.web.LoadingState
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewNavigator
import com.kevinnzou.web.rememberWebViewState


@Composable
fun WebBrowserScreen() {
    
    val url by remember { mutableStateOf(WebConstants.DC_URL) }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()

    var textFieldValue by remember(state.lastLoadedUrl) {
        mutableStateOf(state.lastLoadedUrl)
    }

    val loadingState = state.loadingState

   // A custom WebViewClient and WebChromeClient can be provided via subclassing
    val webClient = remember {
        object : AccompanistWebViewClient() {
            override fun onPageStarted(
                view: WebView,
                url: String?,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                Log.d("Accompanist WebView", "Page started loading for $url")
            }
        }
    }

    Column {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (navigator.canGoForward) {
                        IconButton(onClick = { navigator.navigateForward() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Forward"
                            )
                        }
                    }
                    Text(text = stringResource(id = R.string.web_browser))
                }
            },
            navigationIcon = {
                if (navigator.canGoBack) {
                    IconButton(onClick = { navigator.navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            actions = {
                IconButton(
                    onClick = { navigator.reload() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Back"
                    )
                }
                IconButton(
                    onClick = {
                        textFieldValue?.let {
                            navigator.loadUrl(it)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Back"
                    )
                }
            }
        )

        Row(
            modifier = Modifier.padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textFieldValue ?: "",
                onValueChange = { textFieldValue = it },
                modifier = Modifier.weight(9f)
            )
            if (state.errorsForCurrentRequest.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color.Red,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }

        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = { loadingState.progress },
                modifier = Modifier.fillMaxWidth(),
            )
        }

        WebView(
            state = state,
            modifier = Modifier
                .weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = webClient
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WebBrowserScreenPreview() {
    AmphitriteTheaterTheme {
        WebBrowserScreen()
    }
}
