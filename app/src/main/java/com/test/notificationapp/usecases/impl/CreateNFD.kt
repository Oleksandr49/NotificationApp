package com.test.notificationapp.usecases.impl

import com.test.notificationapp.data.NDFRepository
import com.test.notificationapp.data.NotificationFragmentData
import com.test.notificationapp.usecases.base.BaseCompletableUseCase
import com.test.notificationapp.usecases.base.CreateUseCase
import io.reactivex.CompletableObserver

class CreateNFD (private val repository: NDFRepository): BaseCompletableUseCase(), CreateUseCase<NotificationFragmentData> {
    override fun create(entity: NotificationFragmentData, observer: CompletableObserver) {
        repository.create(entity).also { execute(it, observer) }
    }
}