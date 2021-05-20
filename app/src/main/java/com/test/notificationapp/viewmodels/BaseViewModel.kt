package com.test.notificationapp.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    val compositeDisposable = CompositeDisposable()

    fun dispose(){
        compositeDisposable.dispose()
    }
}