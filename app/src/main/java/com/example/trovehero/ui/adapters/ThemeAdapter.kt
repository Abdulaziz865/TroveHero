package com.example.trovehero.ui.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trovehero.R
import com.example.trovehero.databinding.ItemThemeBinding


class ThemeAdapter(private val onClickListener: (id: String) -> Unit) :
    ListAdapter<String, ThemeAdapter.ThemeViewHolder>(diffUtil) {

    private var context: Context? = null

    fun safeContext(context: Context?) {
        this.context = context
    }

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class ThemeViewHolder(private val binding: ItemThemeBinding) :
        ViewHolder(binding.root) {

        fun onBind(item: String) {
            binding.nameTheme.text = item
            if (bindingAdapterPosition == selectedPosition) {
                binding.nameTheme.setTextColor(Color.BLACK)
            } else {
                binding.nameTheme.setTextColor(Color.WHITE)
            }
        }

        init {

            itemView.setOnClickListener {
                val previousSelectedPosition = selectedPosition
                selectedPosition = bindingAdapterPosition

                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedPosition)

                val item = binding.nameTheme.text.toString()
                getItem(bindingAdapterPosition)?.apply { onClickListener(item) }

                Log.e("eee", item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        return ThemeViewHolder(
            ItemThemeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }

        val backgroundColor =
            if (holder.bindingAdapterPosition == selectedPosition) Color.WHITE else Color.TRANSPARENT
        holder.itemView.backgroundTintList = ColorStateList.valueOf(backgroundColor)

        if (holder.bindingAdapterPosition == selectedPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.item_animation)
            holder.itemView.startAnimation(animation)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}