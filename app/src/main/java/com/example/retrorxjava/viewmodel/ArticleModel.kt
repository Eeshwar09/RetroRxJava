package com.example.retrorxjava.viewmodel

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.retrorxjava.network.Api

class ArticleModel( ) :BaseViewModel() {
     @SuppressLint("SetJavaScriptEnabled")
     fun loadeUrl(text: String, webview: WebView, progressBar: ProgressBar) {
        var redirect: Boolean = false
        var loadingFinished = true

        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.settings.builtInZoomControls = true
        webview.settings.displayZoomControls = false
        webview.settings.setSupportZoom(true)
        webview.isVerticalScrollBarEnabled = false
        val data = Uri.parse(text)
        val url = data.toString()
        if (url.contains("pdf")) {
            val uRl = "https://docs.google.com/gview?embedded=true&url=$url"
            webview.loadUrl(uRl)

        } else {
            webview.loadUrl(url)
        }


        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (!loadingFinished) {
                    redirect = true
                }

                loadingFinished = false
                if (data.toString().contains(".pdf")) {
                    val data = "http://docs.google.com/gview?embedded=true&url=$data"
                    webview.loadUrl(data)
                }
                webview.loadUrl(data.toString())
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {

                progressBar.visibility = View.GONE;

            }
        }
    }



}