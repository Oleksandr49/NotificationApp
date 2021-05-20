package com.test.notificationapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NotificationFragmentDataDao {
    @Insert
    fun create(param: NotificationFragmentData): Completable
    @Query("DELETE FROM NotificationPages WHERE pageNumber = :param")
    fun delete(param: Long): Completable
    @Query("SELECT * FROM NotificationPages")
    fun getAll(): Single<List<NotificationFragmentData>>
}