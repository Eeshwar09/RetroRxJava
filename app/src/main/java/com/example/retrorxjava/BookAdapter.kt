@file:Suppress("DEPRECATION")

package com.example.retrorxjava

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrorxjava.model.BookResponse

class BookAdapter(
    private val bookList: ArrayList<BookResponse> = arrayListOf()

) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookViewHolder(inflater.inflate(R.layout.item_list, parent, false))


    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setList(list: List<BookResponse>) {
        bookList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindModel(bookList[position])


    }
}

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookId: TextView = itemView.findViewById(R.id.bookId)
    private val bookName: TextView = itemView.findViewById(R.id.bookName)
    private val bookUrl: TextView = itemView.findViewById(R.id.bookUrl)
    private val bookAuthor: TextView = itemView.findViewById(R.id.author)


    fun bindModel(movie: BookResponse) {
        val d = (movie.items)
         d.forEach {
             bookId.text = it.id
             bookName.text = it.title
             bookUrl.text = it.url
             bookAuthor.text = it.author

        }

    }

}