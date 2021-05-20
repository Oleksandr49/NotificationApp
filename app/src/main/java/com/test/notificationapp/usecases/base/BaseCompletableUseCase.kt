package com.test.notificationapp.usecases.base

import io.reactivex.Completable
import io.reactivex.CompletableObserver

abstract class BaseCompletableUseCase: BaseUseCase(){

    protected fun execute(observable:Completable, observer:CompletableObserver){
        observable.subscribeOn(threadExecutorScheduler).observeOn(postExecutionThreadScheduler).subscribe(observer)
    }
}