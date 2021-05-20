package com.test.notificationapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.notificationapp.data.NotificationPage
import kotlin.math.max

class NotificationPagerAdapter(fragmentActivity:FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private var fragments = ArrayList<NotificationFragment>()

    fun getItemPositionById(id: Long) = max(fragments.indexOfFirst { it.fragmentPage == id }, 0)

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemId(position: Int): Long {
        return fragments[position].fragmentPage
    }

    override fun containsItem(itemId: Long): Boolean {
        for(item in fragments){
            if(item.fragmentPage == itemId) return true
        }
        return false
    }

    fun initList(dataList: List<NotificationPage>){
        ArrayList<NotificationFragment>().also {
            for(item in dataList){
                it.add(NotificationFragment.getInstance(item.pageNumber))
            }
            updateList(it)
        }
    }

    private fun updateList(updatedList: ArrayList<NotificationFragment>){
        DiffUtil.calculateDiff(NotificationDiffUtilCallback(fragments, updatedList)).also {fragments = updatedList
            it.dispatchUpdatesTo(this)}
    }
}

private class NotificationDiffUtilCallback(val oldList: List<NotificationFragment>, val newList: List<NotificationFragment>):
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].fragmentPage == newList[newItemPosition].fragmentPage

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].fragmentPage == newList[newItemPosition].fragmentPage
}