package com.example.retrorxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrorxjava.R
import com.example.retrorxjava.helper.NetworkHelper
import com.example.retrorxjava.model.Book
import com.example.retrorxjava.model.BookResponse
import com.example.retrorxjava.viewmodel.Apiresult
import com.example.retrorxjava.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("CAST_NEVER_SUCCEEDS")
class BookActivity : AppCompatActivity(), Apiresult {
    private val mainViewModel by viewModel<BookViewModel>()
    private var spaceInfoList: ArrayList<Book> = ArrayList()


    private val adapter: BookAdapter by lazy { BookAdapter(this,spaceInfoList) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        toolbar.title = getString(R.string.app_name)

        books_list.layoutManager = LinearLayoutManager(this)
        books_list.setHasFixedSize(true)
        books_list.adapter = adapter

        when {
            NetworkHelper.isNetworkConnected(this) -> mainViewModel.response(this)
            else -> {
                Toast.makeText(this, "No Internet!", Toast.LENGTH_SHORT).show()

            }
        }


    }

    override fun onSucess(data: Any?) {
        val bookResponse = data as BookResponse
        setAdapter(bookResponse.items as List<Book>)


    }

    override fun onError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun setAdapter(bookList: List<Book>) {
        adapter.setList(bookList)
        adapter.notifyDataSetChanged()
    }
}
