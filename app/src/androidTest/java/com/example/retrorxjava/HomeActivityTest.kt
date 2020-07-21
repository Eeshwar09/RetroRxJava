@file:Suppress("DEPRECATION")

package com.example.retrorxjava

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.retrorxjava.home.model.NewsResponse
import com.example.retrorxjava.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
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
import com.example.retrorxjava.home.di.MyCustomApp
import com.example.retrorxjava.home.di.createOkHttpClient
import com.example.retrorxjava.home.di.retrofit
import com.example.retrorxjava.home.utils.AppConfig
import com.google.protobuf.Api
import kotlinx.android.synthetic.main.activity_book.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION")
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest : AndroidJUnitRunner() {
    private val app: MyCustomApp = ApplicationProvider.getApplicationContext()
//    private val homeViewModelTest= BookAdapter("")


    @get:Rule
    var activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java, true, false)

    private val listItem = 1
    private val modules = module {
        single {
            createOkHttpClient()
        }
        single { retrofit(AppConfig.baseUrl) }
        single {
            get<Retrofit>().create(Api::class.java)
        }

        viewModel {
            HomeViewModel(hackerNewsApi = get())
        }
    }

    @Before
    fun setUp() {
        stopKoin()
        startKoin {

            app.loadModules(modules) {
                activityTestRule.launchActivity(Intent())

            }
        }


    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun activity_visible() {
        onView(withId(R.id.news_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.news_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

//        onView(withId(R.id.bookTitle)).check(matches(withText(listItemInTest.title)))
//        onView(withText("desaiguddu")).check(matches(isDisplayed()))
//        onView(withId(R.id.author)).check(matches(withText("desaiguddu")))


    }

    @Test
    fun list_items() {

        val adapter = activityTestRule.activity
            .news_list.adapter
//        adapter =homeViewModelTest
        activityTestRule.activity.findViewById<RecyclerView>(R.id.news_list).itemAnimator = null

        Assert.assertEquals(0, adapter?.itemCount)
        Assert.assertEquals(
            "User1",
            activityTestRule.activity.news_list[0].findViewById<TextView>(R.id.author).text
        )
        activityTestRule.activity.news_list[0].performClick()
        activityTestRule.activity.news_list[0]


    }

    @Test
    fun recyclerview() {
        Espresso.onView(withId(R.id.news_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.NewsViewHolder>(
                4,
                ViewActions.click()
            )
        )
    }

}