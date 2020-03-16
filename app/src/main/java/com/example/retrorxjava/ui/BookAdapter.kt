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


@Suppress("UNCHECKED_CAST")
class BookAdapter(
    private var context: Context,
    private var bookList: List<Any> = emptyList()

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
class BookViewHolder(private val binding: ItemListBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    private val date = binding.datepublished


    @SuppressLint("SimpleDateFormat")
    fun bindModel(it: Book, bookList: ArrayList<Book>) {
        binding.book = it
        val datePublished = DateHelper.Date(it.date_published.toString())
        date.text = datePublished

        itemView.setOnClickListener {
            val pos = adapterPosition
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra("Position", pos)
            intent.putExtra("BookList", bookList)
            context.startActivity(intent)
        }

    }


}