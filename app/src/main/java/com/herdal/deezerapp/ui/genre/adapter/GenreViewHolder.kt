package com.herdal.deezerapp.ui.genre.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemGenreBinding
import com.herdal.deezerapp.domain.uimodel.Genre
import com.herdal.deezerapp.utils.extensions.executeWithAction

class GenreViewHolder(
    private val binding: ItemGenreBinding,
    private val genreClickListener: GenreClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: Genre) = binding.apply {
        binding.executeWithAction {
            this.genre = genre
        }

        root.setOnClickListener {
            genre.id?.let { genreClickListener.onGenreClick(it) }
        }
    }
}