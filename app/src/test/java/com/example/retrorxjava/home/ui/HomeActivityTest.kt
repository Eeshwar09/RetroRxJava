@file:Suppress("DEPRECATION")

package com.example.retrorxjava.home.ui

import android.content.Intent
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import android.os.Build
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.retrorxjava.home.AndroidTest
import com.example.retrorxjava.home.MyCustomAppTest
import com.example.retrorxjava.home.di.*
import com.example.retrorxjava.home.network.HackerNewsApi
import com.example.retrorxjava.home.utils.AppConfig
import com.example.retrorxjava.home.viewmodel.HomeViewModel
import junit.framework.Assert
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.*
import kotlinx.android.synthetic.main.toolbar_back_arrow.view.*
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.junit.After
import org.junit.Before
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import retrofit2.Retrofit


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
    @Before
    fun setup(){
        app.loadModules(modules) {
            ActivityScenario.launch(HomeActivity::class.java)
            setupActivity()
        }
    }

    @Test
    fun checkScreenContents() {

        Assert.assertEquals(View.VISIBLE, activity.news_list.visibility)


    }

    @Test
    fun adapter() {
//            val list = ArrayList<News>()
//            val recycler = activity.findViewById<RecyclerView>(R.id.news_list)
//            val t = recycler.findViewHolderForAdapterPosition(1)!!.itemView
//            t.performClick()
//            assertEquals(
//                "User1",
//                t.findViewById<TextView>(R.id.author).text.toString()
//            )
//
//            recycler.measure(0, 0)
//            recycler.layout(0, 0, 100, 1000)
//            recycler.adapter = NewsListAdapter(list)


    }

    @Test
    fun toolbar_title() {
        val toolbar = activity.toolbar
        val toolbarTitle = toolbar.titleName.text.toString()
        assertEquals("HackerNews", toolbarTitle)


    }

    @Test
    fun item_viewclicklistner() {
        //val it:News =ArrayList<News>()

        val tvHelloWorld1 =
            activity.news_list.adapter

        if (tvHelloWorld1 != null) {
            Assert.assertEquals(0, tvHelloWorld1.itemCount)
        }
        val t = activity.news_list.findViewHolderForAdapterPosition(99)
        if (t != null) {
            t.itemView.performClick()
            Assert.assertEquals(View.VISIBLE, activity.author.visibility)


        }

//            assertEquals(
//                "User1",
//              t
//            )



    }

    @After
    fun tear_down() {
        stopKoin()
    }



}