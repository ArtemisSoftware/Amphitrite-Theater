package com.artemissoftware.amphitritetheater.web.webviewcustom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WebViewCustomViewModel() : ViewModel() {

    var state = mutableStateOf<WebViewCustomState>(WebViewCustomState())

    fun onWebPageFinished(title: String, canGoBack: Boolean, url: String){

        state.value = state.value.copy(
            title = title,
            canGoBack = canGoBack,
            url = url,
        )
    }

    fun postMessage(message: String){
        state.value = state.value.copy(
            postMessage = message,
            showToast = true
        )
    }

    fun closeToast(){
        state.value = state.value.copy(
            showToast = false
        )
    }
}