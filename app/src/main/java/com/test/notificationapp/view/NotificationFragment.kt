package com.test.notificationapp.view

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.test.notificationapp.R
import com.test.notificationapp.databinding.NotificationFragmentBinding
import com.test.notificationapp.viewmodels.CommonViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NotificationFragment : Fragment() {

    var fragmentPage = 0L
    private val viewModel: CommonViewModel by sharedViewModel()

    companion object {
        fun getInstance(pageNumber: Long): NotificationFragment {
            return NotificationFragment().also { it.fragmentPage = pageNumber }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        NotificationFragmentBinding.inflate(inflater, container, false).also { binding ->
            binding.plusBtn.setOnClickListener { viewModel.addToAdapter(fragmentPage + 1) }
            binding.minusBtn.also { button ->
                if (fragmentPage != 1L) button.setOnClickListener {
                    viewModel.removeFromAdapter(fragmentPage)
                    activity?.let { context ->
                        NotificationManagerCompat.from(context.applicationContext).cancel(fragmentPage.toInt())
                    }
                }
                else
                    button.hide()
            }
            binding.fragmentNumber.text = fragmentPage.toString()
            binding.createNotificationBtn.setOnClickListener { createNotification() }
            return binding.root
        }
    }

    private fun createNotification() {
        activity.let { activity ->
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra("page", fragmentPage)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(activity, fragmentPage.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = activity?.let {
                    NotificationCompat.Builder(it.applicationContext, "First")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("You`ve created notification")
                        .setContentText("Notification $fragmentPage")
                        .setContentIntent(pendingIntent)
                        .build()
                }
            activity?.applicationContext?.let {
                if (notification != null) {
                    NotificationManagerCompat.from(it).notify(fragmentPage.toInt(), notification)
                }
            }
        }
    }
}