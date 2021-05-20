package com.test.notificationapp.data

import android.content.Context

class SharedPreferenceRepository(private val context: Context) {

    fun getNextIndex(): Long {
        val preferences = context.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)
        var index = preferences.getLong("max_index", 1)
        index++
        preferences.edit().putLong("max_index", index).apply()
        return index
    }
}