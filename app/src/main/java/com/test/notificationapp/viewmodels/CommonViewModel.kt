package com.test.notificationapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.test.notificationapp.data.NotificationPage
import com.test.notificationapp.usecases.base.BaseCompletableObserver
import com.test.notificationapp.usecases.base.CreateUseCase
import com.test.notificationapp.usecases.base.DeleteUseCase
import com.test.notificationapp.usecases.base.NextIndexUseCase

class CommonViewModel(
    private val creationUseCase: CreateUseCase<NotificationPage>,
    private val deletionUseCase: DeleteUseCase<NotificationPage>,
    private val getNextIndexUseCase: NextIndexUseCase
) : BaseViewModel() {

    val addedToAdapter = MutableLiveData<Long>()
    val removedFromAdapter = MutableLiveData<Long>()

    fun addToAdapter() {
        val index = getNextIndexUseCase.getNextIndex()
        creationUseCase.create(NotificationPage(index),
            BaseCompletableObserver({ addedToAdapter.postValue(index) },
                { disposable -> compositeDisposable.add(disposable) })
        )
    }

    fun removeFromAdapter(pageNumber: Long) {
        deletionUseCase.delete(pageNumber,
            BaseCompletableObserver({ removedFromAdapter.postValue(pageNumber) },
                { disposable -> compositeDisposable.add(disposable) })
        )
    }
}