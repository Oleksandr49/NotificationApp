package com.test.notificationapp.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.test.notificationapp.R
import com.test.notificationapp.databinding.ActivityMainBinding
import com.test.notificationapp.viewmodels.ActivityViewModel
import com.test.notificationapp.viewmodels.CommonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by viewModel()
    private val sharedViewModel: CommonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.addedToAdapter.observe(this, {viewModel.updateAdapterItems()})
        sharedViewModel.removedFromAdapter.observe(this, {viewModel.updateAdapterItems()})
        ActivityMainBinding.inflate(layoutInflater).also { binding ->
            ExamplePagerAdapter(this).also { adapter ->
                viewModel.adapterItems.observe(this, {list ->
                    if(list.isNotEmpty()){
                        adapter.initList(list)
                        binding.pager.adapter?.let{
                            binding.pager.currentItem = it.itemCount-1
                        }
                    }
                    else sharedViewModel.addToAdapter(1L)})
                binding.pager.adapter = adapter
                viewModel.updateAdapterItems()}

            createNotificationChannel()
            setContentView(binding.root)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "FirstChannel"
            val descriptionText = "For app notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("First", name, importance).apply { description = descriptionText }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        viewModel.dispose()
        sharedViewModel.dispose()
        super.onDestroy()
    }
}