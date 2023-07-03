package com.example.trovehero.ui.fragments.chat

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.trovehero.R
import com.example.trovehero.data.models.BotMessage
import com.example.trovehero.data.models.Message
import com.example.trovehero.data.models.UserMessage
import com.example.trovehero.data.texts.TextResources
import com.example.trovehero.databinding.FragmentChatBinding
import com.example.trovehero.ui.adapters.ChatAdapter
import com.example.trovehero.utils.emptyRvList
import com.google.android.material.snackbar.Snackbar

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val binding by viewBinding(FragmentChatBinding::bind)
    private val chatAdapter = ChatAdapter()
    private val navArgs by navArgs<ChatFragmentArgs>()
    private var secondAndMoreMessages = false
    private val listMessages = ArrayList(chatAdapter.currentList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun initialize() {
        binding.rvListOfMessages.adapter = chatAdapter
        chatAdapter.submitList(listMessages)
        if (emptyRvList) {
            binding.txtStartChat.visibility = View.INVISIBLE
        } else {
            binding.txtStartChat.visibility = View.VISIBLE
        }
        changePhone()
    }

    private fun setupListeners() = with(binding) {
        btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_menuFragment)
        }
        btnSendMessage.setOnClickListener {
            val newMessage = etMessage.text.toString().trim()
            if (newMessage.isNotEmpty()) {
                binding.progressBarForBtn.apply {
                    progress = 100f
                    setProgressWithAnimation(0F, 2000)
                    progressBarWidth = 5f
                    backgroundProgressBarWidth = 7f
                    progressBarColor = Color.WHITE
                }
                addMessage(UserMessage("", newMessage))
                emptyRvList = true
                binding.txtStartChat.visibility = View.INVISIBLE
                rvListOfMessages.scrollToPosition(listMessages.size - 1)
                btnSendMessage.isClickable = false
                binding.btnSendMessage.setImageResource(R.drawable.empty)
                startTimerAndSendMessage()
                etMessage.text.clear()
            } else {
                Snackbar.make(binding.root, "Напишите сообщение", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun addMessage(message: Message) {
        listMessages.add(message)
        chatAdapter.submitList(listMessages)
    }

    private fun startTimerAndSendMessage() {
        val countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.progressBarForBtn.visibility = View.VISIBLE
            }

            override fun onFinish() {
                binding.progressBarForBtn.visibility = View.INVISIBLE
                binding.btnSendMessage.isClickable = true
                binding.btnSendMessage.setImageResource(R.drawable.btn_send)
                addMessage(BotMessage("", sendMessageToBot()))
                chatAdapter.notifyDataSetChanged()
            }
        }

        countDownTimer.start()
    }

    private fun sendMessageToBot(): String {
        val value = if (!secondAndMoreMessages) {
            when (navArgs.theme) {
                "Приключения" -> TextResources.THEME_ADVENTURES_TEXT
                "Искусство" -> TextResources.THEME_ART_TEXT
                "Наука" -> TextResources.THEME_THE_SCIENCE_TEXT
                "Фитнес" -> TextResources.THEME_FITNESS_TEXT
                "Этика" -> TextResources.THEME_ETHICS_TEXT
                "События" -> TextResources.THEME_EVENTS_TEXT
                "Хобби" -> TextResources.THEME_HOBBY_TEXT
                "Экология" -> TextResources.THEME_ECOLOGY_TEXT
                "Стиль" -> TextResources.THEME_STYLE_TEXT
                "Культура" -> TextResources.THEME_CULTURE_TEXT
                "Языки" -> TextResources.THEME_LANGUAGES_TEXT
                "Музыка" -> TextResources.THEME_MUSIC_TEXT
                "Образование" -> TextResources.THEME_EDUCATION_TEXT
                "Саморазвития" -> TextResources.THEME_SELF_DEVELOPMENT_TEXT
                "Социальные медиа и влияние" -> TextResources.THEME_SOCIAL_MEDIA_AND_INFLUENCE_TEXT
                "Космос и астрономия" -> TextResources.THEME_SPACE_AND_ASTRONOMY_TEXT
                "Ментальная арифметика и головоломки" -> TextResources.THEME_MENTAL_ARITHMETIC_AND_PUZZLES_TEXT
                "Климатические изменения" -> TextResources.THEME_CLIMATE_CHANGE_TEXT
                "Фильмы" -> TextResources.THEME_FILMS_TEXT
                else -> TextResources.THEME_ADVENTURES_TEXT
            }
        } else {
            TextResources.SCRIPT_TEXT
        }
        secondAndMoreMessages = true
        return value
    }

    private fun changePhone() {
        when (navArgs.theme) {
            "Приключения" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#91B3FA"))
            "Искусство" -> binding.chatPhone.setBackgroundColor(Color.RED)
            "Наука" -> binding.chatPhone.setBackgroundColor(Color.YELLOW)
            "Фитнес" -> binding.chatPhone.setBackgroundColor(Color.GRAY)
            "Этика" -> binding.chatPhone.setBackgroundColor(Color.BLACK)
            "События" -> binding.chatPhone.setBackgroundColor(Color.CYAN)
            "Хобби" -> binding.chatPhone.setBackgroundColor(Color.MAGENTA)
            "Экология" -> binding.chatPhone.setBackgroundColor(Color.GREEN)
            "Стиль" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#FF03DAC5"))
            "Культура" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#FF6200EE"))
            "Языки" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#FF3700B3"))
            "Музыка" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#C0CA33"))
            "Образование" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#F4511E"))
            "Саморазвития" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#D81B60"))
            "Социальные медиа и влияние" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#D500F9"))
            "Космос и астрономия" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#1DE9B6"))
            "Ментальная арифметика и головоломки" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#000CFF"))
            "Климатические изменения" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#FF00B7"))
            "Фильмы" -> binding.chatPhone.setBackgroundColor(Color.parseColor("#CD1212"))
            else -> binding.chatPhone.setBackgroundColor(Color.parseColor("#91B3FA"))
        }
    }
}