package com.example.retrorxjava.Network

import com.example.retrorxjava.model.BookResponse
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface Api {

    @GET("frontpage.jsonfeed")
    fun getMovies() : Observable<BookResponse>
}