package com.example.retrorxjava.model

import java.io.Serializable

data class BookResponse(
    var version: String? = null,
    var title: String? = null,
    var description: String? = null,
    var home_page_url: String? = null,
    var items: List<Book>? = null
): Serializable