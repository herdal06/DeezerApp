package com.herdal.deezerapp.ui.artist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemArtistBinding
import com.herdal.deezerapp.domain.uimodel.Artist

class ArtistAdapter(
    private val artistClickListener: ArtistClickListener
) : RecyclerView.Adapter<ArtistViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(
            oldItem: Artist,
            newItem: Artist
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Artist,
            newItem: Artist
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this, diffUtil)

    var artists: List<Artist>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding, artistClickListener)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount() = artists.size
}