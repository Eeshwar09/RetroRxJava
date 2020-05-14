package com.example.retrorxjava

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.retrorxjava.di.createOkHttpClient
import com.example.retrorxjava.di.provideForecastApi
import com.example.retrorxjava.di.retrofit
import com.example.retrorxjava.model.BookResponse
import com.example.retrorxjava.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnitRunner
import com.example.retrorxjava.ui.BookActivity
import com.example.retrorxjava.ui.BookAdapter
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matchers.greaterThan
import org.junit.*
import org.junit.runner.RunWith


@Suppress("CAST_NEVER_SUCCEEDS")
@RunWith(AndroidJUnit4::class)
class BookActivityTest : AndroidJUnitRunner() {
    @get:Rule
    var rule = ActivityTestRule(BookActivity::class.java)

    @Test
    fun onCreate() {
        val modules = module {
            single {
                provideForecastApi(retrofit(createOkHttpClient()))
            }
            single {
            }
            single {
                get<Retrofit>().create(BookResponse::class.java)
            }

            viewModel {
                BookViewModel(api = get())
            }
        }



        onView(withId(R.id.titlename))
        //   .check(matches(withText(stringToBetyped)))

    }

    @Test
    fun adapter() {
        val activity = rule.activity
        val id = activity.findViewById<RecyclerView>(R.id.books_list)
        val viewById = activity.findViewById<RecyclerView>(R.id.books_list)
        rule.launchActivity(null)
        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(ListView::class.java))
        val listView = viewById as ListView
        val adapter = listView.getAdapter()
        assertThat(adapter, instanceOf(BookAdapter::class.java))
        assertThat(adapter.count, greaterThan(5))

    }
}