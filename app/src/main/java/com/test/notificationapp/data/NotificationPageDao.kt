package com.test.notificationapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NotificationPageDao {
    @Insert
    fun create(param: NotificationPage): Completable
    @Query("DELETE FROM NotificationPages WHERE pageNumber = :param")
    fun delete(param: Long): Completable
    @Query("SELECT * FROM NotificationPages")
    fun getAll(): Single<List<NotificationPage>>
}