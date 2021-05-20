package com.test.notificationapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotificationPage::class], version = 1)
abstract class NotificationPagesDatabase: RoomDatabase() {
    abstract fun notificationPageDao(): NotificationPageDao
}