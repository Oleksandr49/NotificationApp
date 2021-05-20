package com.test.notificationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager = findViewById(R.id.pager)
        ExamplePagerAdapter(this).also {
            pager.adapter = it
            val fragment = ExampleFragment.getInstance()
            fragment.callBack = object : FragmentCallback {
                override fun addToAdapter(fragment: ExampleFragment) {
                    it.addFragment(fragment)
                }
                override fun removeFromAdapter(fragment: ExampleFragment) {
                    it.removeFragment(fragment)
                }
            }
            it.addFragment(fragment)
            }
    }

    override fun onBackPressed() {
            if (pager.currentItem == 0) {
                super.onBackPressed()
            } else {
                pager.currentItem = pager.currentItem - 1
            }
    }
}