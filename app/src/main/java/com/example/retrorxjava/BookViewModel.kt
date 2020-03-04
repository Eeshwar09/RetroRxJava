package com.example.retrorxjava

import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.MutableLiveData
import com.example.retrorxjava.Network.Api
import com.example.retrorxjava.model.BookResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BookViewModel(
    private val api: Api
) : BaseViewModel() {
    var showBooksList1 = MutableLiveData<MutableList<BookResponse>>()


    override fun onViewAttached(firstAttach: Boolean) {
        super.onViewAttached(firstAttach)

    }

    fun data(list:List<BookResponse>) {


        val showBooksList = api.getMovies()
            .subscribeOn(Schedulers.io()) // designate worker thread (background)
            .observeOn(AndroidSchedulers.mainThread())  //designate observer thread (main thread)



        showBooksList.subscribe(object : Observer<BookResponse> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe : : called. ")
                addToDisposable(d)
            }

            override fun onNext(t: BookResponse) {
                Log.d(TAG, "onNext: : " + Thread.currentThread().name)
                Log.d(TAG, "onNext: : " + (t.items.forEach {
                    it.id

                }
                        )

                )


            }


            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: :", e)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete: : called. ")
            }
        })


    }

    override fun onCleared() {

        super.onCleared()
    }
}

//Create an Observable
//Apply an operator to the Observable
//Designate what thread to do the work on and what thread to emit the results to
//Subscribe an Observer to the Observable and view the results


