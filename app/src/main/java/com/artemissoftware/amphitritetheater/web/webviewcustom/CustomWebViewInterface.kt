package com.artemissoftware.amphitritetheater.web.webviewcustom

class CustomWebViewInterface(private val postMessage: (String) -> Unit) {
    @android.webkit.JavascriptInterface
    fun postMessageFromWeb(message: String) {
        // Handle the message received from the web page
        println("Message received from web: $message")
        postMessage.invoke(message)
    }
}