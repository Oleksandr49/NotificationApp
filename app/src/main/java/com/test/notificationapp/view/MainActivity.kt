package com.test.notificationapp.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.notificationapp.databinding.ActivityMainBinding
import com.test.notificationapp.viewmodels.ActivityViewModel
import com.test.notificationapp.viewmodels.CommonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by viewModel()
    private val sharedViewModel: CommonViewModel by viewModel()
    private var binding: ActivityMainBinding? = null

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.extras?.let {
            goToPosition(it.getLong("page", 0L))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationPage = intent.extras?.getLong("page", 0L) ?: 0L

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        NotificationPagerAdapter(this).also { adapter ->
            binding?.pager?.adapter = adapter
        }

        viewModel.updateAdapterItems(1)
        sharedViewModel.addedToAdapter.observe(this, { viewModel.updateAdapterItems(2) })
        sharedViewModel.removedFromAdapter.observe(this, {viewModel.updateAdapterItems(3) })

        viewModel.adapterItems.observe(this, { pair ->
            if (pair.second.isNotEmpty()) {
                (binding?.pager?.adapter as NotificationPagerAdapter?)?.initList(pair.second)
                when(pair.first) {
                    1 -> goToPosition(notificationPage)
                    2 -> goToPosition(0)
                }
            } else
                sharedViewModel.addToAdapter()
        })
        goToPosition(notificationPage)
        createNotificationChannel()
    }

    private fun goToPosition(notificationPage: Long) {
        binding?.pager?.let { pager ->
            pager.post {
                if (notificationPage != 0L)
                    pager.currentItem = (pager.adapter as NotificationPagerAdapter?)?.getItemPositionById(notificationPage) ?: 0
                else
                    pager.currentItem = (pager.adapter as NotificationPagerAdapter?)?.itemCount ?: 1 - 1
            }
        }
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

    override fun onDestroy() {
        viewModel.dispose()
        sharedViewModel.dispose()
        binding = null
        super.onDestroy()
    }
}