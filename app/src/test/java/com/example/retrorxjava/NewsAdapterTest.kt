@file:Suppress("DEPRECATION")

package com.example.retrorxjava

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import org.junit.Test
import org.junit.Assert.*
import com.example.retrorxjava.home.model.News
import com.example.retrorxjava.home.ui.NewsListAdapter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mockito.mock
import org.mockito.Mockito
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
class NewsAdapterTest {
    private var adapter: NewsListAdapter? = null
    private var mockView: View? = null
    private var mockFragment: Fragment? = null
    private val newsList :ArrayList<News> = ArrayList<News>()


    @Before
    @Throws(Exception::class)
    fun setUp() {
        stopKoin()
        startKoin {

            adapter = NewsListAdapter(newsList = null)
            mockView = mock(View::class.java)
            mockFragment = mock(Fragment::class.java)
        }



    }
    @After
    fun tearDown(){
        stopKoin()
    }
    @Test
    fun getItemCount() {
        val t= ArrayList<News>()
        val userList = listOf(News("name1"))
        val testObject = NewsListAdapter(userList)
        assertEquals(1, testObject.itemCount)
    }

    @Test
    fun getItemCountReturnsNoItems() {
        val userList = emptyList<Any>()
        val initialExpected = 0
        assertEquals(initialExpected, userList.size)

    }
    @Test
    fun itemCount() {
        val candy = News()
        adapter?.setList(Arrays.asList(candy, candy, candy))
        assertEquals(3,adapter?.getItemCount())
    }


    @Test
    fun bindViewSetsHolder() {
        val myClass =mock<News>(News::class.java)
        val userInfo = (News())
        val userList = ArrayList<News>()
        val testObject = NewsListAdapter(userList)
        val viewHolder: NewsListAdapter.NewsViewHolder = mock()


    }

}