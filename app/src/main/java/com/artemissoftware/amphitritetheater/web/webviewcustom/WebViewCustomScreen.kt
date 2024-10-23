@file:OptIn(ExperimentalMaterial3Api::class)

package com.artemissoftware.amphitritetheater.web.webviewcustom

import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.web.WebConstants

@Composable
fun WebViewCustomScreen(
    viewModel: WebViewCustomViewModel
) {
    WebViewCustomContent(
        state = viewModel.state.value,
        onWebPageFinished = viewModel::onWebPageFinished,
        postMessage = viewModel::postMessage,
        closeToast = viewModel::closeToast,
    )
}

@Composable
private fun WebViewCustomContent(
    state: WebViewCustomState,
    onWebPageFinished: (String, Boolean, String) -> Unit,
    postMessage: (String) -> Unit,
    closeToast: () -> Unit,
) {

    val context = LocalContext.current

    val customWebView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true

            webViewClient = CustomWebViewClient(
                onPageFinished = onWebPageFinished
            )

            addJavascriptInterface(CustomWebViewInterface(postMessage = postMessage), "Android")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = state.title) },
                navigationIcon = {
                    if(state.canGoBack) {
                        IconButton(onClick = {
                            customWebView.goBack()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    }
                },
            )
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color =  Color.Gray)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = "Status")
                Text(text = "Url: " + (state.url ?: "No url"))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(
                        onClick = { customWebView.loadUrl(WebConstants.REDIRECT_LOCAL_URL) },
                    ) {
                        Text("Redirect to url change test")
                    }

                    Button(
                        onClick = { customWebView.loadUrl(WebConstants.POST_MESSAGE_LOCAL_URL) },
                    ) {
                        Text("Post message test")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                AndroidView(
                    factory = { ctx ->
                        customWebView
                    },
                    update = {
                        it.loadUrl(WebConstants.CUSTOM_WEB_VIEW_PAGE_LOCAL_URL)
                    }
                )
            }

            if (state.showToast) {
                Toast.makeText(context, state.postMessage, Toast.LENGTH_SHORT).show()
                closeToast()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WebViewCustomContentPreview() {
    AmphitriteTheaterTheme {
        WebViewCustomContent(
            state = WebViewCustomState(),
            onWebPageFinished = {_, _, _ ->},
            postMessage = {_, ->},
            closeToast = { },
        )
    }
}