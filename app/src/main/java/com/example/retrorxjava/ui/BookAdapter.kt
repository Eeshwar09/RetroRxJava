@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrorxjava.databinding.ItemListBinding
import com.example.retrorxjava.helper.DateHelper
import com.example.retrorxjava.model.Book
import java.text.SimpleDateFormat
import java.util.*

class BookAdapter(
    var context: Context,
    var bookList: List<Book> = emptyList()

) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding, context)


    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setList(list: List<Book>) {
        this.bookList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindModel(bookList[position])
    }
}

@Suppress("CAST_NEVER_SUCCEEDS")
class BookViewHolder(val binding: ItemListBinding, val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    val bookUrl = binding.bookUrl
    val title = binding.bookTitle
    val id = binding.datepublished




    @SuppressLint("SimpleDateFormat")
    fun bindModel(it: Book) {

        binding.book = it
        val date = it.date_published
        val formateedDate=DateHelper.Date(date!!)
        id.setText(formateedDate)

        title.setOnClickListener {
            val url = bookUrl.getText().toString()
            val bookTitle = title.getText().toString()
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra("webUrl", url)
            intent.putExtra("title",bookTitle)
            context.startActivity(intent)

        }


    }


}