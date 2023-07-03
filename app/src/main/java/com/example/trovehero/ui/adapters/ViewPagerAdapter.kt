package com.example.trovehero.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trovehero.ui.fragments.onboard.OnBoardPagingFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int) = OnBoardPagingFragment().apply {
        arguments = Bundle().apply {
            putInt(OnBoardPagingFragment.ARG_ONBOARD_POSITION, position)
        }
    }
}