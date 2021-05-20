package com.test.notificationapp.usecases.impl

import com.test.notificationapp.data.NDFRepository
import com.test.notificationapp.data.NotificationFragmentData
import com.test.notificationapp.usecases.base.BaseCompletableUseCase
import com.test.notificationapp.usecases.base.DeleteUseCase
import io.reactivex.CompletableObserver

class DeleteNFD (private val repository: NDFRepository): BaseCompletableUseCase(), DeleteUseCase<NotificationFragmentData>{

    override fun delete(param: Long, observer: CompletableObserver) {
        repository.delete(param).also { execute(it, observer) }
    }
}