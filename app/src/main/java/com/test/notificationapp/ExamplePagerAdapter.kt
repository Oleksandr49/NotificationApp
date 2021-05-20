package com.test.notificationapp

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter

class ExamplePagerAdapter(fragmentActivity:FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private var fragments = ArrayList<ExampleFragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: ExampleFragment){
        Log.i("debug", "Add fragment ${fragment.fragmentPage}")
        val list = ArrayList<ExampleFragment>()
        list.addAll(fragments)
        list.add(fragment)
        updateList(list)
    }

    fun removeFragment(fragment: ExampleFragment){
        Log.i("debug", "Remove fragment ${fragment.fragmentPage}")
        val list = ArrayList<ExampleFragment>()
        for(item in fragments){
            if(item.fragmentPage != fragment.fragmentPage)list.add(item)
        }
        updateList(list)
    }

    override fun getItemId(position: Int): Long {
        return fragments[position].fragmentPage.toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        for(item in fragments){
            if(item.fragmentPage.toLong() == itemId) return true
        }
        return false
    }

    private fun updateList(updatedList: ArrayList<ExampleFragment>){
        Log.i("debug", "Updating list, oldSize:${fragments.size}, newSize:${updatedList.size}")
        DiffUtil.calculateDiff(ExampleDiffUtilCallback(fragments, updatedList)).also {fragments = updatedList
            it.dispatchUpdatesTo(this)}
    }
}

private class ExampleDiffUtilCallback(val oldList: List<ExampleFragment>, val newList: List<ExampleFragment>):
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].fragmentPage == newList[newItemPosition].fragmentPage

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].fragmentPage == newList[newItemPosition].fragmentPage
}