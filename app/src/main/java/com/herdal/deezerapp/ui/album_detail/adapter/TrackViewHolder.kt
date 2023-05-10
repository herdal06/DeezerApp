package com.herdal.deezerapp.ui.album_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemTrackBinding
import com.herdal.deezerapp.domain.uimodel.Track
import com.herdal.deezerapp.utils.extensions.executeWithAction

class TrackViewHolder(
    private val binding: ItemTrackBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(track: Track) = binding.apply {
        binding.executeWithAction {
            this.track = track
        }
    }
}