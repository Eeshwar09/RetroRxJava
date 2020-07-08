package com.example.retrorxjava.home.ui

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import android.os.Build
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import android.webkit.WebView
import com.example.retrorxjava.R
import com.example.retrorxjava.web.ui.WebActivity
import com.example.retrorxjava.home.viewmodel.KoinTest
import org.assertj.core.api.Assertions


@Suppress("DEPRECATION")

@Config(sdk = [Build.VERSION_CODES.P])

@RunWith(
    RobolectricTestRunner::class
)
class WebActivityTest:KoinTest {
    var activity: WebActivity? = Robolectric.setupActivity(WebActivity::class.java)

    @Before
    fun setUp() {
        stopKoin()
        activity = Robolectric.buildActivity(WebActivity::class.java).create().get()

    }

    @Test
    fun shouldRecordLastLoadedUrl() {
        val webView = activity!!.findViewById<WebView>(R.id.webview)
        webView.loadUrl("http://example.com")
        Assertions.assertThat(shadowOf(webView).lastLoadedUrl).isEqualTo("http://example.com")
    }


    @After
    fun tearDown() {
        activity != null
    }

    @Test
    fun onBackPressed() {

    }

    @Test
    fun onclick() {

    }


}