package com.herdal.deezerapp.ui.artist_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemAlbumBinding
import com.herdal.deezerapp.domain.uimodel.Album
import com.herdal.deezerapp.utils.extensions.executeWithAction

class AlbumViewHolder(
    private val binding: ItemAlbumBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(album: Album) = binding.apply {
        binding.executeWithAction {
            this.album = album
        }
    }
}