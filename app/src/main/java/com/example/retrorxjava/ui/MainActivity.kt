@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrorxjava.viewmodel.Apiresult
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.retrorxjava.viewmodel.BookViewModel
import com.example.retrorxjava.model.BookResponse


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), Apiresult {


    private val mainViewModel by viewModel<BookViewModel>()
    private val adapter: BookAdapter by lazy { BookAdapter() }


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        books_list?.layoutManager = LinearLayoutManager(this)
        books_list?.setHasFixedSize(true)
        books_list?.adapter = adapter

        when{
            NetworkHelper.isNetworkConnected(this) -> mainViewModel.response(this)
            else -> {
                Toast.makeText(this, "No Internet!", Toast.LENGTH_SHORT).show()

            }
        }
        mainViewModel.showBooksList1.observe(this, Observer {
           // setAdapter(it)
        })






    }

    override fun onSucess(data: Any) {
        setAdapter(mainViewModel.showBooksList1)

    }

    override fun onError(error: String) {
    }

    private fun setAdapter(bookList: MutableLiveData<BookResponse>) {

        val itemList = bookList.value
        adapter.setList(itemList!!.items)
        adapter.notifyDataSetChanged()


    }
    object NetworkHelper{
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
    }
}





