package com.example.retrorxjava.model

import com.google.gson.annotations.SerializedName

data class BookResponse (
    @SerializedName("version")
    var version: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("home_page_url")
    var home_page_url: String? = null,
    @SerializedName("items")
     var items : MutableList<Book>
)