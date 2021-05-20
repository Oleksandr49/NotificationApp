package com.test.notificationapp.data

import io.reactivex.Completable
import io.reactivex.Single

interface BaseRepository<T> {

    fun create(param:T) : Completable
    fun delete(param:Long) : Completable
    fun getAll() : Single<List<T>>
}