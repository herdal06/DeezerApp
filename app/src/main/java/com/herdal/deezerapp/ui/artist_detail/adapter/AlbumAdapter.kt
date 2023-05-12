package com.herdal.deezerapp.ui.artist_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.herdal.deezerapp.databinding.ItemAlbumBinding
import com.herdal.deezerapp.domain.uimodel.Album

class AlbumAdapter(
    private val albumClickListener: AlbumClickListener
) : RecyclerView.Adapter<AlbumViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(
            oldItem: Album,
            newItem: Album
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Album,
            newItem: Album
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this, diffUtil)

    var albums: List<Album>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding, albumClickListener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount() = albums.size
}