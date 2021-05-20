package com.test.notificationapp

interface FragmentCallback {

    fun addToAdapter(fragment: ExampleFragment)
    fun removeFromAdapter(fragment: ExampleFragment)
}