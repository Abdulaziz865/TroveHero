package com.example.trovehero.ui.fragments.onboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.trovehero.utils.PreferenceHelper
import com.example.trovehero.R
import com.example.trovehero.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment(R.layout.fragment_on_board_paging) {

    private val binding by viewBinding(FragmentOnBoardPagingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                binding.btnStart.setOnClickListener {

                }
            }
            1 -> {
                binding.imageOnboard.visibility = View.GONE
                binding.txtDescription.text = getString(R.string.txt_description)

                binding.btnStart.setOnClickListener {
                    PreferenceHelper.isStartApp = true
                    findNavController().navigate(R.id.action_onBoardFragment_to_chatFragment)
                }
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}