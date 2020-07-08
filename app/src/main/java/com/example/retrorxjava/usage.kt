package com.example.retrorxjava

import android.app.Application
import com.example.retrorxjava.home.model.News
import com.example.retrorxjava.home.model.NewsResponse
import com.example.retrorxjava.home.network.HackerNewsApi
import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}
