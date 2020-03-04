package com.example.retrorxjava

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.retrorxjava.model.BookResponse


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var bookAdapter: BookAdapter
    private val mainViewModel by viewModel<BookViewModel>()



    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movies_list?.adapter = bookAdapter
        movies_list?.layoutManager = LinearLayoutManager(this)



            mainViewModel.showBooksList1.observe(this, Observer<List<BookResponse>> {
                if (it != null) {

                    bookAdapter.setList(it)

                }
                else{
                    mainViewModel.data(it)
                }

            })






    }


}


