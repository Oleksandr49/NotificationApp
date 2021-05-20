package com.test.notificationapp.usecases.impl

import com.test.notificationapp.data.NDFRepository
import com.test.notificationapp.data.NotificationFragmentData
import com.test.notificationapp.usecases.base.BaseSingleUseCase
import com.test.notificationapp.usecases.base.ReadAllUseCase
import io.reactivex.SingleObserver

class GetAllNFD(private val repository: NDFRepository): BaseSingleUseCase<List<NotificationFragmentData>>(), ReadAllUseCase<NotificationFragmentData> {

    override fun readAll(observer: SingleObserver<List<NotificationFragmentData>>) {
        repository.getAll().also { execute(it, observer) }
    }
}