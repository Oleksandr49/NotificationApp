package com.test.notificationapp.usecases.impl

import com.test.notificationapp.data.BaseRepository
import com.test.notificationapp.data.NotificationPagesRepository
import com.test.notificationapp.data.NotificationPage
import com.test.notificationapp.usecases.base.BaseCompletableUseCase
import com.test.notificationapp.usecases.base.CreateUseCase
import io.reactivex.CompletableObserver

class NotificationPageCreationUseCase (private val repository: BaseRepository<NotificationPage>): BaseCompletableUseCase(), CreateUseCase<NotificationPage> {
    override fun create(entity: NotificationPage, observer: CompletableObserver) {
        repository.create(entity).also { execute(it, observer) }
    }
}