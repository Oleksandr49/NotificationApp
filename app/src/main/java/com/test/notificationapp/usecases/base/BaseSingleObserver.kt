package com.test.notificationapp.usecases.base

import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class BaseSingleObserver<T>(private val callBack: (param:T)-> Unit,
                            private val disposableAction: (disposable:Disposable)->Unit): SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {
        disposableAction(d)
    }

    override fun onSuccess(t: T) {
        callBack(t)
    }

    override fun onError(e: Throwable) {
        Log.i("Error", e.message.toString())
    }
}