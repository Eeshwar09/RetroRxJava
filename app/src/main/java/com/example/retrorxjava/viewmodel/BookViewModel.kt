package com.example.retrorxjava.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.retrorxjava.network.Api
import com.example.retrorxjava.model.BookResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class BookViewModel(
    private val api: Api
) : BaseViewModel() {
    var showBooksList1 = MutableLiveData<BookResponse>()


    private var compositeDisposable = CompositeDisposable()


    fun response(callbck: Apiresult) {

        compositeDisposable.add(
            api.getMovies().subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({
               callbck.onSucess(it)
            }, {
               callbck.onError("error")
            })
        )

    }


}

interface Apiresult {
    fun onSucess(data: Any?)
    fun onError(error: String?)
}

//Create an Observable
//Apply an operator to the Observable
//Designate what thread to do the work on and what thread to emit the results to
//Subscribe an Observer to the Observable and view the results


