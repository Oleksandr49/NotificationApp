package com.test.notificationapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.test.notificationapp.data.NotificationPage
import com.test.notificationapp.usecases.base.BaseCompletableObserver
import com.test.notificationapp.usecases.base.CreateUseCase
import com.test.notificationapp.usecases.base.DeleteUseCase

class CommonViewModel(private val creationUseCase: CreateUseCase<NotificationPage>,
                      private val deletionUseCase: DeleteUseCase<NotificationPage>): BaseViewModel() {

    val addedToAdapter = MutableLiveData<Long>()
    val removedFromAdapter = MutableLiveData<Boolean>()

    fun addToAdapter(pageNumber:Long){
        NotificationPage(pageNumber).also {
            creationUseCase.create(it, BaseCompletableObserver({addedToAdapter.postValue(pageNumber)},{ disposable -> compositeDisposable.add(disposable) }))}
    }

    fun removeFromAdapter(pageNumber:Long){
        deletionUseCase.delete(pageNumber, BaseCompletableObserver({removedFromAdapter.postValue(true)},{disposable -> compositeDisposable.add(disposable)}))
    }
}