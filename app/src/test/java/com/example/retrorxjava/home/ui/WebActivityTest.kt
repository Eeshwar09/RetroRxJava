@file:Suppress("DEPRECATION")

package com.example.retrorxjava.home.ui

import android.content.Intent
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import android.os.Build
import android.view.View
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import android.webkit.WebView
import android.widget.ImageButton
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.retrorxjava.R
import com.example.retrorxjava.home.AndroidTest
import com.example.retrorxjava.home.MyCustomAppTest
import com.example.retrorxjava.home.di.createOkHttpClient
import com.example.retrorxjava.home.di.retrofit
import com.example.retrorxjava.home.network.HackerNewsApi
import com.example.retrorxjava.home.utils.AppConfig
import com.example.retrorxjava.web.ui.WebActivity
import com.example.retrorxjava.web.viewmodel.WebViewModel
import junit.framework.Assert
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.*
import org.assertj.core.api.Assertions
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit


@Suppress("DEPRECATION")


@RunWith(
    RobolectricTestRunner::class
)
@Config(application = MyCustomAppTest::class, sdk = [Build.VERSION_CODES.P])

class WebActivityTest: AndroidTest<WebActivity>() {
    private val app: MyCustomAppTest = ApplicationProvider.getApplicationContext()



    override fun createActivityInstance(intent: Intent?): WebActivity {
        return Robolectric.buildActivity(WebActivity::class.java, intent)
            .create()
            .resume()
            .get()

    }

    val module = module {
        single {
            createOkHttpClient()
        }

        single { retrofit(AppConfig.baseUrl) }


        single {
            get<Retrofit>().create(HackerNewsApi::class.java)
        }
        viewModel {
            WebViewModel()
        }

    }

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            app.loadModules(module) {
                ActivityScenario.launch(WebActivity::class.java)
                setupActivity()
            }

        }


    }

    @Test
    fun shouldRecordLastLoadedUrl() {
        val webView = activity.findViewById<WebView>(R.id.webview)
        webView.loadUrl("http://example.com")
        Assertions.assertThat(shadowOf(webView).lastLoadedUrl).isEqualTo("http://example.com")
    }


    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun onBackPressed() {

        val nextButton = activity.findViewById<ImageButton>(R.id.backButton)
        nextButton.performClick()
        val intent = shadowOf(activity).peekNextStartedActivity()
        Assert.assertEquals(
            HomeActivity::class.java.canonicalName,
            intent.component?.className
        )


    }


    @Test
    fun checkScreen() {
        Assert.assertEquals(View.VISIBLE, activity.web_activity.visibility)
        Assert.assertEquals(View.VISIBLE, activity.next.visibility)
        Assert.assertEquals(View.VISIBLE, activity.toolbar.visibility)
    }
    @Test
    fun nextButtonClick(){
        Assert.assertEquals(View.VISIBLE, activity.web_activity.visibility)
        Assert.assertEquals(View.VISIBLE, activity.next.visibility)
    }
    @Test
    fun previousButtonClick(){
        Assert.assertEquals(View.VISIBLE, activity.web_activity.visibility)
        Assert.assertEquals(View.VISIBLE, activity.next.visibility)
        Assert.assertEquals(View.VISIBLE, activity.toolbar.visibility)
    }



}