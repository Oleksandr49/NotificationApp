package com.test.notificationapp.usecases.base

import io.reactivex.SingleObserver

interface ReadAllUseCase<E>{
    fun readAll(observer: SingleObserver<List<E>>)
}