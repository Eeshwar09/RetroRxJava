package com.example.retrorxjava.home.ui

import android.content.Intent
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.retrorxjava.R
import com.example.retrorxjava.home.AndroidTest
import com.example.retrorxjava.home.MyCustomAppTest
import com.example.retrorxjava.home.di.*
import com.example.retrorxjava.home.model.News
import com.example.retrorxjava.home.network.HackerNewsApi
import com.example.retrorxjava.home.utils.AppConfig
import com.example.retrorxjava.home.viewmodel.HomeViewModel
import junit.framework.Assert
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.view.*
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.junit.After
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.ArrayList


@Suppress("DEPRECATION", "UNREACHABLE_CODE")

@RunWith(
    RobolectricTestRunner::class
)
@Config(application = MyCustomAppTest::class, sdk = [Build.VERSION_CODES.P])

class HomeActivityTest : AndroidTest<HomeActivity>() {
    private val app: MyCustomAppTest = ApplicationProvider.getApplicationContext()
    override fun createActivityInstance(intent: Intent?): HomeActivity {

        return Robolectric.buildActivity(HomeActivity::class.java, intent)
            .create()
            .resume()
            .get()

    }


    @Test
    fun adapter() {
        val list = ArrayList<News>()
        val context = activity.applicationContext
        val recycler = activity.findViewById<RecyclerView>(R.id.news_list)
        recycler.findViewHolderForAdapterPosition(1)?.itemView?.performClick()
        recycler.measure(0, 0)
        recycler.layout(0, 0, 100, 1000)
        recycler.adapter = NewsListAdapter(list)
    }

    val modules = module {
        single {
            createOkHttpClient()
        }

        single { retrofit(AppConfig.baseUrl) }


        single {
            get<Retrofit>().create(HackerNewsApi::class.java)
        }

        viewModel {
            HomeViewModel(hackerNewsApi = get())
        }
    }

    @Test
    fun checkScreenContents() {

        app.loadModules(modules) {
            // Start mocking from here
            ActivityScenario.launch(HomeActivity::class.java)
            setupActivity()
            Assert.assertEquals(View.VISIBLE, activity.news_list.visibility)

        }
    }

        @Test
        fun toolbar_title() {
            app.loadModules(modules) {
                ActivityScenario.launch(HomeActivity::class.java)
                setupActivity()
                val tvHelloWorld =
                    activity.toolbar
                val title1 = tvHelloWorld.titleName.text.toString()
                assertEquals("HackerNews", title1)
            }

    }

    @After
    fun tear_down() {
        stopKoin()
    }

    @Test
    fun onclick() {

    }


}