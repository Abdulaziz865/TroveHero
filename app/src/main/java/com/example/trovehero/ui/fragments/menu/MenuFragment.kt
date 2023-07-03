package com.example.trovehero.ui.fragments.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.trovehero.R
import com.example.trovehero.databinding.FragmentMenuBinding
import com.example.trovehero.utils.emptyRvList
import com.google.android.material.snackbar.Snackbar

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnNewChat.setOnClickListener {
            emptyRvList = false
            findNavController().navigate(R.id.action_menuFragment_to_chatFragment)
        }
        binding.btnPhoto.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Приложение находится в разработке и пока ещё не имеет полного функционала",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        binding.btnSpeak.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Приложение находится в разработке и пока ещё не имеет полного функционала",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        binding.btnMusic.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Приложение находится в разработке и пока ещё не имеет полного функционала",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        binding.btnTheme.setOnClickListener {
            emptyRvList = false
            findNavController().navigate(R.id.action_menuFragment_to_themeFragment)
        }
    }
}