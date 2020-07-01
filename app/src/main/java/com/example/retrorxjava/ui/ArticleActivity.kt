@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrorxjava.R
import android.webkit.WebView
import android.view.View
import com.example.retrorxjava.model.Book
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.*
import com.example.retrorxjava.viewmodel.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION", "NAME_SHADOWING", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
class ArticleActivity : AppCompatActivity() {
    private var position: Int = 0
    private var bookLists: Int? = null
    private var size: Int? = 0
    private var bookList:ArrayList<Book> ?=ArrayList<Book>()
    private val mainViewModel by viewModel<ArticleModel>()


    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    @SuppressWarnings("unchecked")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val intent = this.intent
        position = intent.getIntExtra("Position", 0)
        bookList = intent.getSerializableExtra("BookList") as ArrayList<Book>?
        bookLists = bookList?.size
        size = bookLists?.minus(2)
        bookList?.get(position)?.url?.let { mainViewModel.loadeUrl(it,webview,progressBar) }
        titlename.text = bookList?.get(position)?.title
        buttonsEnable()

        next.setOnClickListener {
            position++
            buttonsEnable()
            mainViewModel.loadeUrl(bookList!![position].url!!,webview,progressBar)
            titlename.text = bookList!![position].title

        }
        previous.setOnClickListener {
            position--
            buttonsEnable()
            mainViewModel.loadeUrl(bookList!![position].url!!,webview,progressBar)
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


    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, BookActivity::class.java).apply {

            }
        }
    }
}
