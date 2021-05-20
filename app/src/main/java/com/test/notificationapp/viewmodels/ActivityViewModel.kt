package com.test.notificationapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.test.notificationapp.data.NotificationFragmentData
import com.test.notificationapp.usecases.base.BaseSingleObserver
import com.test.notificationapp.usecases.base.ReadAllUseCase


class ActivityViewModel(private val getAllUseCase: ReadAllUseCase<NotificationFragmentData>): BaseViewModel() {

    val adapterItems = MutableLiveData<List<NotificationFragmentData>>()

    fun updateAdapterItems(){
        getAllUseCase.readAll(BaseSingleObserver({list -> adapterItems.postValue(list)},{disposable -> compositeDisposable.add(disposable) }))
    }
}