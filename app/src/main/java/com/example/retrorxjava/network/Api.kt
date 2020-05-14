package com.example.retrorxjava.network

import com.example.retrorxjava.model.BookResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("frontpage.jsonfeed")
    fun getMovies() : Observable<BookResponse>
}
//https://hnrss.org/frontpage.jsonfeed