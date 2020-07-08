package com.example.retrorxjava.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel():ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    protected open fun onViewAttached(firstAttach: Boolean) {

    }

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()

    }

}