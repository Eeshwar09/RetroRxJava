package com.example.retrorxjava.model

import com.google.gson.annotations.SerializedName

data class Book(


    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("content_html")
    var content_html: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("external_url")
    var external_url: String? = null,
    @SerializedName("date_published")
    var date_published: String? = null,
    @SerializedName("author")
    var author: String? = null



)



