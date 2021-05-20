package com.test.notificationapp.usecases.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase {
    protected open val threadExecutorScheduler: Scheduler = Schedulers.io()
    protected open val postExecutionThreadScheduler: Scheduler = AndroidSchedulers.mainThread()
}