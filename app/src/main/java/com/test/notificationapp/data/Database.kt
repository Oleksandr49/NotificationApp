package com.test.notificationapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotificationFragmentData::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun dao(): NotificationFragmentDataDao
}