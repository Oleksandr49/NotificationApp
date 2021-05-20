package com.test.notificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("debug", "Activity onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        pager = findViewById(R.id.pager)
        ExamplePagerAdapter(this).also {
            /*
            if(savedInstanceState == null){
                val test = getPreferences(MODE_PRIVATE).getInt("MaxPage", 0)
                it.updateList(restoreAdapterItems(test))
                Log.i("debug", "Restored from preferences: $test")
            }

             */
            pager.adapter = it
            val fragment = ExampleFragment.getInstance()
            fragment.callBack = object : FragmentCallback {
                override fun addToAdapter(fragment: ExampleFragment) {
                    it.addFragment(fragment)
                    pager.currentItem = fragment.fragmentPage
                }
                override fun removeFromAdapter(fragment: ExampleFragment) {
                    it.removeFragment(fragment)
                }
            }
            it.addFragment(fragment)
            }
    }

    override fun onStart() {
        Log.i("debug", "Activity onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.i("debug", "Activity onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.i("debug", "Activity onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.i("debug", "Activity onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("debug", "Activity onDestroy()")
        /*
        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt("MaxPage", ExampleFragment.getMaxPage())
        editor.apply()
        Log.i("debug", "Activity MaxPAge saved: ${ExampleFragment.getMaxPage()}")

         */
        super.onDestroy()
    }

    override fun onRestart() {
        Log.i("debug", "Activity onRestart()")
        super.onRestart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i("debug", "Activity onRestoreInstanceState()")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("debug", "Activity onSaveInstanceState()")
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
            if (pager.currentItem == 0) {
                super.onBackPressed()
            } else {
                pager.currentItem = pager.currentItem - 1
            }
    }

    private fun restoreAdapterItems(maxPage:Int):ArrayList<ExampleFragment>{
        val list = ArrayList<ExampleFragment>()
        var counter = 0
        while (counter < maxPage){
            list.add(ExampleFragment.getInstance())
            counter++
        }
           return list
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "FirstChannel"
            val descriptionText = "For app notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("First", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}