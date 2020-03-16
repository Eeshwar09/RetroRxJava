package com.example.retrorxjava.model

import java.io.Serializable


data class Book(
    var id: String? = null,
    var title: String? = null,
    var content_html: String? = null,
    var url: String? = null,
    var external_url: String? = null,
    var date_published: String? = null,
    var author: String? = null

):Serializable



