package com.test.notificationapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotificationPages")
data class NotificationPage(@PrimaryKey(autoGenerate = false) val pageNumber:Long)