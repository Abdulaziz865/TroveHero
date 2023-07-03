package com.example.trovehero.ui.fragments.onboard

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.trovehero.ui.adapters.ViewPagerAdapter
import com.example.trovehero.utils.PreferenceHelper
import com.example.trovehero.R
import com.example.trovehero.databinding.FragmentOnBoardBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment(R.layout.fragment_on_board) {

    private val binding by viewBinding(FragmentOnBoardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        onPage()
        checkPreference()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
            //Some implementation
        }.attach()
    }

    private fun initialize() {
        binding.viewPager.adapter = ViewPagerAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager) {
    }

    private fun onPage() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        val btnStart = view?.findViewById<Button>(R.id.btn_start)
                        btnStart?.setOnClickListener {
                            if (binding.viewPager.currentItem < 1){
                                binding.viewPager.setCurrentItem( binding.viewPager.currentItem + 1, true)
                            }
                        }
                    }
                    1 -> {
                    }
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun checkPreference() {
        if (PreferenceHelper.isStartApp) {
            findNavController().navigate(R.id.chatFragment)
        }
    }
}