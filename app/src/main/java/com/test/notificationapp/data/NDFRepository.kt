package com.test.notificationapp.data

class NDFRepository(private val dao: NotificationFragmentDataDao): BaseRepository<NotificationFragmentData> {

    override fun create(param: NotificationFragmentData) = dao.create(param)

    override fun delete(param: Long) = dao.delete(param)

    override fun getAll() = dao.getAll()
}