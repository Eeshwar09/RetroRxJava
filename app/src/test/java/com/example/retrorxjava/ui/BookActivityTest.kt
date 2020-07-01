package com.example.retrorxjava.ui

import android.content.Context
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.example.retrorxjava.R
import com.example.retrorxjava.model.BookResponse
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.hamcrest.core.IsEqual.equalTo


@Suppress("DEPRECATION", "UNREACHABLE_CODE")
@Config(sdk = [Build.VERSION_CODES.P])

@RunWith(
    RobolectricTestRunner::class
)
class BookActivityTest {
    var activity: BookActivity = Robolectric.setupActivity(BookActivity::class.java)
   //  var activitsy = Robolectric.buildActivity(BookActivity::class.java).create().get()


    fun setup() {
        activity = Robolectric.buildActivity(BookActivity::class.java).create().get()

    }

    @Test
    fun toolbar_title() {
        val activity = Robolectric.setupActivity(BookActivity::class.java)
        val tvHelloWorld = activity.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val title1 = tvHelloWorld.title
        assertEquals("HackerNews", title1)


    }

    @Test
    fun adapter() {
        val list = listOf<BookResponse>()
        val context = activity.applicationContext
        val recycler = activity.findViewById<RecyclerView>(R.id.books_list)
        recycler.findViewHolderForAdapterPosition(0)?.itemView?.performClick()
        recycler.measure(0, 0)
        recycler.layout(0, 0, 100, 1000)
        recycler.adapter = BookAdapter(context, list)
    }


    fun tear_down() {
        activity= null!!
    }
    @Test
    fun onclick(){

    }


}