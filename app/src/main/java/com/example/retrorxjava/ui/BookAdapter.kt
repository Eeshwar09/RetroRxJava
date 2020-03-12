@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrorxjava.databinding.ItemListBinding
import com.example.retrorxjava.model.Book

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

class BookViewHolder(val binding: ItemListBinding, val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    val b = binding.bookUrl
    val c = binding.bookHtml
    val title = binding.bookTitle
    val comment = binding.externalUrl
    val id = binding.bookId


    fun bindModel(it: Book) {
        binding.book = it
        c.setText(Html.fromHtml(Html.fromHtml(it.content_html!!.removeRange(1, 31)).toString()))
        title.setOnClickListener {
            url(b)

        }
        comment.setOnClickListener {

            url(id)
        }


    }


    private fun url(text: TextView) {
        val url = text.getText().toString()
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        context.startActivity(i)
    }

}