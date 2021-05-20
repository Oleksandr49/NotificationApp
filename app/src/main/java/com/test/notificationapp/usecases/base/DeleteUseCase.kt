package com.test.notificationapp.usecases.base

import io.reactivex.CompletableObserver

interface DeleteUseCase<T> {
    
    fun delete(param:Long, observer:CompletableObserver)
}