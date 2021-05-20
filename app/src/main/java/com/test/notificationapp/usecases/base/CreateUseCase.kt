package com.test.notificationapp.usecases.base

import io.reactivex.CompletableObserver


interface CreateUseCase<E>{
    
    fun create(entity:E, observer:CompletableObserver)
}