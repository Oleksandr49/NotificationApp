package com.test.notificationapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.test.notificationapp.data.NotificationPage
import com.test.notificationapp.usecases.base.BaseSingleObserver
import com.test.notificationapp.usecases.base.ReadAllUseCase


class ActivityViewModel(private val getAllUseCase: ReadAllUseCase<NotificationPage>): BaseViewModel() {

    val adapterItems = MutableLiveData<Pair<Int, List<NotificationPage>>>()

    fun updateAdapterItems(i: Int) {
        getAllUseCase.readAll(BaseSingleObserver({list -> adapterItems.postValue(Pair(i, list))},{disposable -> compositeDisposable.add(disposable) }))
    }
}