package com.example.retrorxjava.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.MailTo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrorxjava.R
import android.webkit.WebView
import android.net.Uri
import android.view.View
import androidx.lifecycle.Observer
import com.example.retrorxjava.model.Book
import com.example.retrorxjava.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION")
class ArticleActivity : AppCompatActivity() {


    private val mainViewModel by viewModel<BookViewModel>()

    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    @SuppressWarnings("unchecked")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.retrorxjava.R.layout.activity_article)
        val intent = this.intent
        var position = intent.getIntExtra("Position", 0)
        val BookList = intent.getSerializableExtra("BookList") as ArrayList<Book>
        BookList.size
        loadurl(BookList[position].url!!)
        titlename.text = BookList[position].title
        ButtonsEnable()
        next.setOnClickListener {
            position++
            loadurl(BookList[position].url!!)
            titlename.text = BookList[position].title

        }
        previous.setOnClickListener {
            position--
            loadurl(BookList[position].url!!)
            titlename.text = BookList[position].title
        }

       backButton.setOnClickListener {
           startActivity(ArticleActivity.intentFor(this))
           finish()
       }
    }

    private fun ButtonsEnable() {
        val position = intent.getIntExtra("Position", 0)
        val BookList = intent.getSerializableExtra("BookList") as ArrayList<Book>
        val size = BookList.size - 1


        if (position == 0) {
            previous.setEnabled(false)
            next.setEnabled(true)
           // next.setVisibility(View.VISIBLE);

        } else if (position == size) {
            previous.isEnabled = true
            next.isEnabled = false
           // next.setVisibility(View.INVISIBLE);

        } else {
            previous.isEnabled = true
            next.isEnabled = true
           // next.setVisibility(View.VISIBLE);

        }
    }

    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    private fun loadurl(text: String) {
        val mWebView = findViewById(R.id.webview) as WebView
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        WebView.setWebContentsDebuggingEnabled(false)
        val data = Uri.parse(text)
        mWebView.loadUrl(data.toString())

    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context,BookActivity::class.java).apply {

            }
        }
    }
}
