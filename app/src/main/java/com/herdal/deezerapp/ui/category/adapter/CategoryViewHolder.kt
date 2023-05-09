package com.herdal.deezerapp.ui.category.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemCategoryBinding
import com.herdal.deezerapp.domain.uimodel.Category
import com.herdal.deezerapp.utils.extensions.executeWithAction

class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) = binding.apply {
        binding.executeWithAction {
            this.category = category
        }
    }
}