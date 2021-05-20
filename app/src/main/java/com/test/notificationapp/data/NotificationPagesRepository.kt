package com.test.notificationapp.data

class NotificationPagesRepository(private val dao: NotificationPageDao): BaseRepository<NotificationPage> {

    override fun create(param: NotificationPage) = dao.create(param)
    override fun delete(param: Long) = dao.delete(param)
    override fun getAll() = dao.getAll()
}