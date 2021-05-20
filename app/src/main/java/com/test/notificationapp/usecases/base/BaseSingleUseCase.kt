package com.test.notificationapp.usecases.base

import io.reactivex.Single
import io.reactivex.SingleObserver

abstract class BaseSingleUseCase<E>: BaseUseCase() {

     protected fun execute(observable: Single<E>, observer: SingleObserver<E>){
        observable.subscribeOn(threadExecutorScheduler).observeOn(postExecutionThreadScheduler).subscribe(observer)
    }
}