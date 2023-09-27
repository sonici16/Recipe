package com.example.myreceipt.adpter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class  PagerFragmentStateAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    private val mFragments = ArrayList<Fragment>()
    private val fmIds = mFragments.map { it.hashCode().toLong() }

    fun addFragment(fragment: Fragment) {
        mFragments.add(fragment)
    }

    fun getFragment(position: Int): Fragment {
        return mFragments[position]
    }
    fun Reload() {
        notifyDataSetChanged()
    }

    override fun containsItem(itemId: Long): Boolean {
        return fmIds.contains(itemId)
    }


    override fun getItemCount(): Int = mFragments.size
    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }


}