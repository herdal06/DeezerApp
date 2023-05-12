package com.herdal.deezerapp.ui.artist_detail.adapter

import com.herdal.deezerapp.domain.uimodel.Album

interface AlbumClickListener {
    fun onAlbumClick(album: Album)
}