package com.artemissoftware.amphitritetheater.web.webviewcustom

import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient(
    private val onPageFinished: (String, Boolean, String) -> Unit,
): WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        view?.let {
            onPageFinished(
                it.title ?: "No title found",
                it.canGoBack(),
                it.url ?: "No url found",
            )
        }
    }
}