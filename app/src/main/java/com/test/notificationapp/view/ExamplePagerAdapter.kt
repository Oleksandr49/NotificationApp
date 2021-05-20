package com.test.notificationapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.notificationapp.data.NotificationFragmentData

class ExamplePagerAdapter(fragmentActivity:FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private var fragments = ArrayList<ExampleFragment>()

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

    fun initList(dataList: List<NotificationFragmentData>){
        ArrayList<ExampleFragment>().also {
            for(item in dataList){
                it.add(ExampleFragment.getInstance(item.pageNumber))
            }
            updateList(it)
        }
    }

    private fun updateList(updatedList: ArrayList<ExampleFragment>){
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