@file:Suppress("DEPRECATION")

package com.example.retrorxjava.home.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrorxjava.web.ui.WebActivity
import com.example.retrorxjava.databinding.ItemListBinding
import com.example.retrorxjava.home.helper.DateHelper
import com.example.retrorxjava.home.model.News
import java.util.ArrayList


@Suppress("UNCHECKED_CAST")
class NewsListAdapter(
    private var newsList: List<News>

) : RecyclerView.Adapter<NewsListAdapter.BookViewHolder>() {
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        context = parent.context
        return BookViewHolder(binding, context)


    }

    override fun getItemCount() =
        newsList.size


    fun setList(list: List<News>) {
        this.newsList = list
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindModel(newsList[position], newsList as ArrayList<News>)
    }


    @Suppress("CAST_NEVER_SUCCEEDS")
    class BookViewHolder(private val binding: ItemListBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        private val date = binding.datepublished


        @SuppressLint("SimpleDateFormat", "CommitPrefEdits")
        fun bindModel(it: News, newsList: ArrayList<News>) {
            binding.news = it
            val datePublished = DateHelper.date(it.date_published.toString())
            date.text = datePublished

            itemView.setOnClickListener {
                val pos = adapterPosition
                val intent = Intent(context, WebActivity::class.java)
                intent.putExtra("Position", pos)
                intent.putExtra("BookList", newsList)
                context.startActivity(intent)
            }

        }


    }
}