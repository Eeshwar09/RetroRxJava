@file:Suppress("DEPRECATION")

package com.example.retrorxjava

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.retrorxjava.home.di.createOkHttpClient
import com.example.retrorxjava.home.di.provideForecastApi
import com.example.retrorxjava.home.di.retrofit
import com.example.retrorxjava.home.model.NewsResponse
import com.example.retrorxjava.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnitRunner
import com.example.retrorxjava.home.ui.HomeActivity
import com.example.retrorxjava.home.ui.NewsListAdapter
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matchers.greaterThan
import org.junit.*
import org.junit.runner.RunWith
import com.example.retrorxjava.web.ui.WebActivity
import androidx.test.platform.app.InstrumentationRegistry


@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION")
@RunWith(AndroidJUnit4::class)
class HomeActivityTest : AndroidJUnitRunner() {
    @get:Rule
    var rule = ActivityTestRule(HomeActivity::class.java)
    private var activity: HomeActivity? = null

    @Test
    fun onCreate() {
        module {

            single {
            }
            single {
                get<Retrofit>().create(NewsResponse::class.java)
            }

            viewModel {
                HomeViewModel(hackerNewsApi = get())
            }
        }



        onView(withId(R.id.titleName))

    }

    @Test
    fun adapter() {
        val activity = rule.activity

        val viewById = activity.findViewById<RecyclerView>(R.id.news_list)
        rule.launchActivity(null)
        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(ListView::class.java))
        val listView = viewById as ListView
        val adapter = listView.adapter
        assertThat(adapter, instanceOf(NewsListAdapter::class.java))
        assertThat(adapter.count, greaterThan(5))


    }

    @Before
    fun setUp() {
        activity = rule.activity


    }

    @After
    fun tearDown() {
        activity = null
    }

    @Test
    fun another() {
        val intent = Intent(
            ApplicationProvider.getApplicationContext<HomeActivity>(),
            WebActivity::class.java
        )
        startActivitySync(intent)
        val targetContext = InstrumentationRegistry.getInstrumentation()
            .targetContext
        val intent1 = Intent(targetContext, WebActivity::class.java)
        intent.putExtra("Name", "Value")
        rule.launchActivity(intent1)

    }

}