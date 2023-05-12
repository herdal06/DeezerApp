package com.herdal.deezerapp.ui.genre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemGenreBinding
import com.herdal.deezerapp.domain.uimodel.Genre

class GenreAdapter(
    private val genreClickListener: GenreClickListener
) : RecyclerView.Adapter<GenreViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this, diffUtil)

    var genres: List<Genre>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding, genreClickListener)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount() = genres.size
}