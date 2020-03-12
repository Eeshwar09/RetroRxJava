package com.example.retrorxjava.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrorxjava.R
import android.webkit.WebView
import android.net.Uri
import com.example.retrorxjava.model.Book
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.*


class ArticleActivity : AppCompatActivity() {

    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val d = Book()
        profilename.text = d.title


        val mWebView = findViewById(R.id.webview) as WebView
        val webUrl = intent.getStringExtra("webUrl")
        val title = intent.getStringExtra("title")
        val data = Uri.parse(webUrl)
        profilename.text = title
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.loadUrl(data.toString())
        WebView.setWebContentsDebuggingEnabled(false)

    }


}
