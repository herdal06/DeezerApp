package com.herdal.deezerapp.ui.album_detail

sealed class AlbumDetailUiEvent {
    data class GetTracksByAlbum(val albumId: Int) : AlbumDetailUiEvent()
}