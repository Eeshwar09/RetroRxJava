@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrorxjava.R
import android.webkit.WebView
import android.net.Uri
import android.view.View
import com.example.retrorxjava.model.Book
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.*
import kotlin.collections.ArrayList
import android.webkit.WebViewClient


@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION", "NAME_SHADOWING",
    "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE"
)
class ArticleActivity : AppCompatActivity() {
    private var position: Int = 0
    private var bookLists: Int = 0
    private var size: Int = 0
    private var bookList: ArrayList<Book>? = null

    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    @SuppressWarnings("unchecked")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val intent = this.intent
        position = intent.getIntExtra("Position", 0)
        bookList = intent.getSerializableExtra("BookList") as ArrayList<Book>
        bookLists = bookList!!.size
        size = bookLists - 1
        loadeUrl(bookList!![position].url!!)
        titlename.text = bookList!![position].title
        buttonsEnable()

        next.setOnClickListener {
            position++
            buttonsEnable()
            loadeUrl(bookList!![position].url!!)
            titlename.text = bookList!![position].title

        }
        previous.setOnClickListener {
            position--
            buttonsEnable()
            loadeUrl(bookList!![position].url!!)
            titlename.text = bookList!![position].title
        }

        backButton.setOnClickListener {
            startActivity(intentFor(this))

        }


    }


    override fun onBackPressed() {
        val myWebView = findViewById<WebView>(R.id.webview)
        if (myWebView != null && myWebView.canGoBack()) {
            position--
            val title = bookList!![position].title
            titlename.text = title
            myWebView.goBack()
            buttonsEnable()
            return
        } else {
            super.onBackPressed()
        }
    }


    private fun buttonsEnable() {
        when (position) {
            0 -> {
                previous.visibility = View.INVISIBLE
                next.visibility = View.VISIBLE

            }
            size -> {
                next.visibility = View.INVISIBLE
                previous.visibility = View.VISIBLE

            }
            else -> {
                next.visibility = View.VISIBLE
                previous.visibility = View.VISIBLE

            }
        }
    }

    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    private fun loadeUrl(text: String) {
        var redirect: Boolean = false
        var loadingFinished = true

        val webview: WebView = findViewById<WebView>(R.id.webview)
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
                this@ArticleActivity.title = view.title
            }
        }
    }
    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, BookActivity::class.java).apply {

            }
        }
    }
}
