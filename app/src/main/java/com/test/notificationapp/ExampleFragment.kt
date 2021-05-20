package com.test.notificationapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExampleFragment: Fragment() {

    var fragmentPage = 0
    var callBack: FragmentCallback? = null

    companion object{
        var maxPageNumber = 0
        fun getInstance():ExampleFragment{
            return ExampleFragment().also {
                maxPageNumber++
                it.fragmentPage = maxPageNumber }
        }
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
            layout.findViewById<FloatingActionButton>(R.id.minus_btn).setOnClickListener {callBack?.removeFromAdapter(this)}
            layout.findViewById<TextView>(R.id.fragment_number).text = fragmentPage.toString()
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