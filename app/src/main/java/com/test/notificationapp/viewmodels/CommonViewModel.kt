package com.test.notificationapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.test.notificationapp.data.NotificationFragmentData
import com.test.notificationapp.usecases.base.BaseCompletableObserver
import com.test.notificationapp.usecases.base.CreateUseCase
import com.test.notificationapp.usecases.base.DeleteUseCase

class CommonViewModel(private val creationUseCase: CreateUseCase<NotificationFragmentData>,
                      private val deletionUseCase: DeleteUseCase<NotificationFragmentData>): BaseViewModel() {

    val addedToAdapter = MutableLiveData<Long>()
    val removedFromAdapter = MutableLiveData<Boolean>()

    fun addToAdapter(pageNumber:Long){
        NotificationFragmentData(pageNumber).also {
            creationUseCase.create(it, BaseCompletableObserver({addedToAdapter.postValue(pageNumber)},{ disposable -> compositeDisposable.add(disposable) }))}
    }

    fun removeFromAdapter(pageNumber:Long){
        deletionUseCase.delete(pageNumber, BaseCompletableObserver({removedFromAdapter.postValue(true)},{disposable -> compositeDisposable.add(disposable)}))
    }
}