@file:Suppress("DEPRECATION")

package com.example.retrorxjava.home.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.retrorxjava.R
import com.example.retrorxjava.base.BaseActivity
import com.example.retrorxjava.home.helper.NetworkHelper
import com.example.retrorxjava.home.model.News
import com.example.retrorxjava.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_book.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION", "UNCHECKED_CAST")
class HomeActivity : BaseActivity() {
    private val homeViewModel by viewModel<HomeViewModel>()


    private lateinit var adapter: NewsListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        initlizeViews()


        when {
            NetworkHelper.isNetworkConnected(this) -> homeViewModel.newsList.observe(
                this,
                Observer {

                    setAdapter(it)
                })
            else -> {
                Toast.makeText(this, "No Internet!", Toast.LENGTH_SHORT).show()

            }
        }


    }

    private fun setAdapter(spaceList: List<News>) {
        if (!::adapter.isInitialized) {
            adapter = NewsListAdapter(spaceList)
            news_list.adapter = adapter
            adapter.notifyDataSetChanged()


        } else {
            adapter.setList(spaceList)
            adapter.notifyDataSetChanged()
        }


    }

    private fun initlizeViews() {
        setScreenTitle(R.string.app_name)


    }


}
