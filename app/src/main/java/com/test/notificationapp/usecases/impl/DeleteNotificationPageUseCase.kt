package com.test.notificationapp.usecases.impl

import com.test.notificationapp.data.BaseRepository
import com.test.notificationapp.data.NotificationPagesRepository
import com.test.notificationapp.data.NotificationPage
import com.test.notificationapp.usecases.base.BaseCompletableUseCase
import com.test.notificationapp.usecases.base.DeleteUseCase
import io.reactivex.CompletableObserver

class DeleteNotificationPageUseCase (private val repository: BaseRepository<NotificationPage>): BaseCompletableUseCase(), DeleteUseCase<NotificationPage>{

    override fun delete(param: Long, observer: CompletableObserver) {
        repository.delete(param).also { execute(it, observer) }
    }
}