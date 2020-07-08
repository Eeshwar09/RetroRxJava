@file:Suppress("DEPRECATION")

package com.example.retrorxjava

import android.content.Context
import org.junit.Test
import org.junit.Assert.*
import com.example.retrorxjava.home.model.News
import com.example.retrorxjava.home.ui.NewsListAdapter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.mock
import org.mockito.Mockito




@Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
class NewsAdapterTest {
    private val context = mock(Context::class.java)

    @Test
    fun getItemCount() {
        val t= ArrayList<News>()
        val userList = listOf(News("name1"))
        val testObject = NewsListAdapter(t)
        assertEquals(0, testObject.itemCount)
    }

    @Test
    fun getItemCountReturnsNoItems() {
        val userList = emptyList<Any>()
        val initialExpected = 0
        assertEquals(initialExpected, userList.size)

    }


    @Test
    fun bindViewSetsHolder() {
        val myClass = Mockito.mock<News>(News::class.java)

        val userInfo = (News())
        val userList = ArrayList<News>()
        val testObject = NewsListAdapter(userList)
        val viewHolder: NewsListAdapter.BookViewHolder = mock()
        //testObject.onBindViewHolder(viewHolder, 0)
        verify(viewHolder).bindModel(myClass, userList)

    }

}