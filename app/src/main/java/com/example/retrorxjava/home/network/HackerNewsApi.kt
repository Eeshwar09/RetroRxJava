package com.example.retrorxjava.home.network

import com.example.retrorxjava.home.model.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface HackerNewsApi {

    @GET("frontpage.jsonfeed")
    fun getNews() : Observable<NewsResponse>


}
//class UserRepository(val userApi: HackerNewsApi) {
//
//    fun getUsers(): Observable<NewsResponse> {
//        return Observable.concatArray(
//
//            getUsersFromApi())
//    }
//
//    private fun getUsersFromApi(): Observable<NewsResponse> {
//        return userApi.getNews()
//            .doOnNext {
////               Timber.d("Dispatching ${it.items} users from API...")
////
//            }
//    }
//
//    }


