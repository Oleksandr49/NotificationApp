package com.test.notificationapp.usecases.base

import android.util.Log
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

class BaseCompletableObserver(private val callBack: ()-> Unit,
                              private val disposableAction: (disposable:Disposable)->Unit): CompletableObserver {

    override fun onSubscribe(d: Disposable) {
        disposableAction(d)
    }

    override fun onComplete(){
        callBack()
    }

    override fun onError(e: Throwable) {
        Log.i("Error", e.message.toString())
    }
}