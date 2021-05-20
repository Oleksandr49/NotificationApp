package com.test.notificationapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.test.notificationapp.data.NotificationPage
import com.test.notificationapp.usecases.base.BaseSingleObserver
import com.test.notificationapp.usecases.base.ReadAllUseCase


class ActivityViewModel(private val getAllUseCase: ReadAllUseCase<NotificationPage>): BaseViewModel() {

    val adapterItems = MutableLiveData<List<NotificationPage>>()

    fun updateAdapterItems() {
        getAllUseCase.readAll(BaseSingleObserver({list -> adapterItems.postValue(list)},{disposable -> compositeDisposable.add(disposable) }))
    }
}