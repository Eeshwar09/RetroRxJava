package com.example.retrorxjava

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
//import org.mockito.MockitoAnnotations
import android.widget.LinearLayout
import androidx.test.platform.app.InstrumentationRegistry
import com.example.retrorxjava.model.Book
import com.example.retrorxjava.model.BookResponse
import com.example.retrorxjava.ui.BookAdapter
import com.google.common.collect.Lists
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.mock
import java.util.ArrayList

//import org.mockito.junit.MockitoJUnitRunner


@Suppress("DEPRECATION")
class BookAdapterTest {
    val context = mock(Context::class.java)

    @Test
    fun getItemCount() {
        val context = mock(Context::class.java)
        val userList = listOf(Book("name1"))
        val testObject = BookAdapter(context, userList)

        assertEquals(1, testObject.itemCount)
    }

    @Test
    fun getItemCountReturnsNoItems() {
        val userList = emptyList<Any>()
        val initialExpected = 0
        assertEquals(initialExpected, userList.size)

    }


    @Test
    fun bindViewSetsHolder() {
        val context = mock(Context::class.java)
        val userInfo = (Book("name1"))
        val userList = listOf(userInfo)
        val testObject = BookAdapter(context, userList)
        val viewHolder: BookAdapter.BookViewHolder = mock()
        testObject.onBindViewHolder(viewHolder, 0)
        verify(viewHolder).bindModel(userInfo)

    }

}