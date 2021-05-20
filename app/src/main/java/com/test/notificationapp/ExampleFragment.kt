package com.test.notificationapp

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExampleFragment: Fragment() {

    var fragmentPage = 0
    var callBack: FragmentCallback? = null

    companion object{
        private var maxPageNumber = 0
        fun getInstance():ExampleFragment{
            return ExampleFragment().also {
                maxPageNumber++
                it.fragmentPage = maxPageNumber }
        }
        fun getMaxPage() = maxPageNumber
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.i("debug", "On create view for page: $fragmentPage")
        if(savedInstanceState != null) {
            savedInstanceState.getInt("pageNumber").also { fragmentPage = it }
            Log.i("debug", "Restoring state for page: $fragmentPage")
        }
        return inflater.inflate(R.layout.notification_fragment, container, false).also {layout ->
            layout.findViewById<FloatingActionButton>(R.id.plus_btn).setOnClickListener {
                getInstance().also { fragment -> fragment.callBack = callBack
                    callBack?.addToAdapter(fragment) }
            }
            layout.findViewById<FloatingActionButton>(R.id.minus_btn).setOnClickListener {
                activity?.let {NotificationManagerCompat.from(it.applicationContext).cancel(fragmentPage)}
                callBack?.removeFromAdapter(this)}
            layout.findViewById<TextView>(R.id.fragment_number).text = fragmentPage.toString()
            layout.findViewById<Button>(R.id.create_notification_btn).setOnClickListener {createNotification()}
        }
    }

    private fun createNotification(){
        Log.i("debug", "Create notification for: $fragmentPage")
        activity?.let {
            val intent = Intent(it, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(it, 0, intent, 0)
            val notification = NotificationCompat.Builder(it.applicationContext, "First")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("You`ve created notification")
                .setContentText("Notification $fragmentPage")
                .setContentIntent(pendingIntent)
                .build()
            NotificationManagerCompat.from(it).notify(fragmentPage, notification)
        }
    }

    override fun onResume() {
        Log.i("debug", "On resume for page: $fragmentPage")
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("debug", "Saving state for page: $fragmentPage")
        outState.putInt("pageNumber",fragmentPage)
        super.onSaveInstanceState(outState)
    }
}