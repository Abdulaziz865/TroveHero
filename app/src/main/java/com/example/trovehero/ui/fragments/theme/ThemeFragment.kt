package com.example.trovehero.ui.fragments.theme

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.trovehero.R
import com.example.trovehero.databinding.FragmentThemeBinding
import com.example.trovehero.ui.adapters.ThemeAdapter
import com.google.android.material.snackbar.Snackbar

class ThemeFragment : Fragment(R.layout.fragment_theme) {

    private val binding by viewBinding(FragmentThemeBinding::bind)
    private var newTheme: String? = null
    private val themeAdapter = ThemeAdapter(this::onClickItem)
    private val listOfThemes = arrayListOf(
        "Приключения",
        "Искусство",
        "Наука",
        "Фитнес",
        "Ментальная арифметика и головоломки",
        "Этика",
        "События",
        "Социальные медиа и влияние",
        "Хобби",
        "Экология",
        "Стиль",
        "Климатические изменения",
        "Культура",
        "Языки",
        "Музыка",
        "Образование",
        "Саморазвития",
        "Космос и астрономия",
        "Фильмы",
        "Благотворительность"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        binding.rvListOfTheme.adapter = themeAdapter
        themeAdapter.submitList(listOfThemes)
        themeAdapter.safeContext(requireContext())
    }

    private fun setupListener() {
        binding.btnSelectTheme.setOnClickListener {
            if (newTheme != null){
                findNavController().navigate(ThemeFragmentDirections.actionThemeFragmentToChatFragment(newTheme))
            }
            else {
                Snackbar.make(binding.root, "Тема не выбрана!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun onClickItem(id: String) {
        newTheme = id
    }
}