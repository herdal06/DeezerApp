package com.herdal.deezerapp.ui.artist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemArtistBinding
import com.herdal.deezerapp.domain.uimodel.Artist
import com.herdal.deezerapp.utils.extensions.executeWithAction

class ArtistViewHolder(
    private val binding: ItemArtistBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artist: Artist) = binding.apply {
        binding.executeWithAction {
            this.artist = artist
        }
    }
}