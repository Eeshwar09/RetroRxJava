@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrorxjava.databinding.ItemListBinding
import com.example.retrorxjava.model.Book

class BookAdapter(
    var bookList: List<Book> = emptyList()

) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)


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

class BookViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    val b = binding.bookHtml


    fun bindModel(it: Book) {
        binding.book = it
        b.setText(Html.fromHtml(Html.fromHtml(it.content_html).toString()))

    }

}