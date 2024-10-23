package com.artemissoftware.amphitritetheater.web.webviewcustom

import com.artemissoftware.amphitritetheater.web.WebConstants.KASA_URL

data class WebViewCustomState(
    val url: String? = null,
    val postMessage: String = "",
    val showToast: Boolean = false,

    val title: String = "No title available",
    val canGoBack: Boolean = false,
    val urlToGo: String? = KASA_URL,
)
