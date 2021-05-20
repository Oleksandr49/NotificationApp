package com.test.notificationapp.usecases.impl

import com.test.notificationapp.data.BaseRepository
import com.test.notificationapp.data.NotificationPagesRepository
import com.test.notificationapp.data.NotificationPage
import com.test.notificationapp.usecases.base.BaseSingleUseCase
import com.test.notificationapp.usecases.base.ReadAllUseCase
import io.reactivex.SingleObserver

class GetAllNotificationPagesUseCase(private val repository: BaseRepository<NotificationPage>): BaseSingleUseCase<List<NotificationPage>>(), ReadAllUseCase<NotificationPage> {

    override fun readAll(observer: SingleObserver<List<NotificationPage>>) {
        repository.getAll().also { execute(it, observer) }
    }
}