@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrorxjava.databinding.ItemListBinding
import com.example.retrorxjava.helper.DateHelper
import com.example.retrorxjava.model.Book
import java.util.ArrayList
import android.os.Bundle
import java.io.Serializable
import androidx.core.content.ContextCompat.startActivity
import android.R.array
import android.widget.Toast


@Suppress("UNCHECKED_CAST")
class BookAdapter(
    var context: Context,
    var bookList: List<Any> = emptyList()

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
        holder.bindModel(bookList[position] as Book, bookList as ArrayList<Book>)
    }
}

@Suppress("CAST_NEVER_SUCCEEDS")
class BookViewHolder(val binding: ItemListBinding, val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    val date = binding.datepublished


    @SuppressLint("SimpleDateFormat")
    fun bindModel(it: Book, booklist: ArrayList<Book>) {
        binding.book = it
        val Date = DateHelper.Date(it.date_published.toString())
        date.text = Date

        itemView.setOnClickListener { v ->
            val pos = adapterPosition
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra("Position", pos)
            intent.putExtra("BookList", booklist)
            context.startActivity(intent)
        }

    }


}