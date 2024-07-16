package com.artemissoftware.amphitritetheater.webview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Network
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen() {

    val context = LocalContext.current
    val loadURL = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var networkReceiver: BroadcastReceiver? = null

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = remember {

        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                loadURL.value = WebConstants.DEFAULT_URL
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                loadURL.value = "file:///android_asset/offline.html"
            }
        }
    }

    networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {

                val connectivityManager =
                    context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo

                if (networkInfo != null && networkInfo.isConnected) {
                    loadURL.value = WebConstants.DEFAULT_URL
                } else {
                    loadURL.value = "file:///android_asset/offline.html"
                }
            }
        }
    }

    val webClient = remember {
        object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                isLoading = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                isLoading = false
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                isLoading = false
                loadURL.value = "file:///android_asset/offline.html"
            }
        }
    }

    LaunchedEffect(key1 = Unit) {

        val filter = IntentFilter().apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }
        context.registerReceiver(networkReceiver, filter)
        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        val networkState = connectivityManager.activeNetworkInfo
        if (networkState != null && networkState.isConnected) {
            loadURL.value = WebConstants.DEFAULT_URL
        } else {
            loadURL.value = "file:///android_asset/offline.html"
        }
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            context.unregisterReceiver(networkReceiver)
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }



    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true

                webViewClient = webClient
            }
        },
        update = {
            it.loadUrl(loadURL.value)
        }
    )

    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }

}
